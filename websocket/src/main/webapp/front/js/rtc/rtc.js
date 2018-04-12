
/* ======================= 连接部分 ======================= */
var isRTCPeerConnection = true;
function initialize() {
	console.log("Initializing; room=${roomKey}.");
	card = document.getElementById("card");
	localVideo = document.getElementById("localVideo");
	miniVideo = document.getElementById("miniVideo");
	remoteVideo = document.getElementById("remoteVideo");
	//resetStatus();
	openChannel();
	getUserMedia();
}
/* 打开通道 */
function openChannel() {
	console.log("Opening channel.");
	//socket = new WebSocket("ws://127.0.0.1:8080/websocket/chat?u=${user}");
	
	var socket = new WebSocket("ws://127.0.0.1:8080/websocket/akk/abc");
	socket.onopen = function() {console.log("open ...");};
	
	socket.onmessage = onChannelMessage;
	socket.onclose = onChannelClosed;
}

function getUserMedia() {
	try {
		navigator.webkitGetUserMedia({
					'audio' : true,
					'video' : true
				}, onUserMediaSuccess, onUserMediaError);
		console.log("Requested access to local media with new syntax.");
	} catch (e) {
		try {
			navigator.webkitGetUserMedia("video,audio", onUserMediaSuccess,
					onUserMediaError);
			console.log("Requested access to local media with old syntax.");
		} catch (e) {
			alert("webkitGetUserMedia() failed. Is the MediaStream flag enabled in about:flags?");
			console.log("webkitGetUserMedia failed with exception: "
					+ e.message);
		}
	}
}

function onUserMediaError()
{
	console.log("user media error");
}

function onUserMediaSuccess(stream) {
	console.log("User has granted access to local media.");
	var url = webkitURL.createObjectURL(stream);
	localVideo.style.opacity = 1;
	localVideo.src = url;
	localStream = stream;
	// Caller creates PeerConnection.
	if (initiator)
		maybeStart();
}

/* ======================= 通信部分 ======================= */

function maybeStart() {
	if (!started && localStream && channelReady) {
		setStatus("Connecting...");
		console.log("Creating PeerConnection.");
		createPeerConnection();
		console.log("Adding local stream.");
		pc.addStream(localStream);
		started = true;
		// Caller initiates offer to peer.
		if (initiator)
			doCall();
	}
}

function doCall() {
	console.log("Sending offer to peer.");
	if (isRTCPeerConnection) {
		pc.createOffer(setLocalAndSendMessage, null, mediaConstraints);
	} else {
		var offer = pc.createOffer(mediaConstraints);
		pc.setLocalDescription(pc.SDP_OFFER, offer);
		sendMessage({
					type : 'offer',
					sdp : offer.toSdp()
				});
		pc.startIce();
	}
}

function setLocalAndSendMessage(sessionDescription) {
	pc.setLocalDescription(sessionDescription);
	sendMessage(sessionDescription);
}

function sendMessage(message) {
	var msgString = JSON.stringify(message);
	console.log('发出信息 : ' + msgString);
	path = 'message?r=${roomKey}' + '&u=${user}';
	var xhr = new XMLHttpRequest();
	xhr.open('POST', path, true);
	xhr.send(msgString);
}

/* ============== 接收部分 =================== */

function onChannelMessage(message) {
	console.log('收到信息 : ' + message.data);
	if (isRTCPeerConnection)
		processSignalingMessage(message.data);//建立视频连接
	else
		processSignalingMessage00(message.data);
}

function onChannelClosed()
{
	console.log("Channel Close...");
}

function processSignalingMessage(message) {
	alert(message);
	var msg = JSON.parse(message);

	if (msg.type === 'offer') {
		// Callee creates PeerConnection
		if (!initiator && !started)
			maybeStart();

		// We only know JSEP version after createPeerConnection().
		if (isRTCPeerConnection)
			pc.setRemoteDescription(new RTCSessionDescription(msg));
		else
			pc.setRemoteDescription(pc.SDP_OFFER,
					new SessionDescription(msg.sdp));

		doAnswer();
	} else if (msg.type === 'answer' && started) {
		pc.setRemoteDescription(new RTCSessionDescription(msg));
	} else if (msg.type === 'candidate' && started) {
		var candidate = new RTCIceCandidate({
					sdpMLineIndex : msg.label,
					candidate : msg.candidate
				});
		pc.addIceCandidate(candidate);
	} else if (msg.type === 'bye' && started) {
		onRemoteHangup();
	}
}
