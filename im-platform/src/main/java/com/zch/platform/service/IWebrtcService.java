package com.zch.platform.service;

import com.zch.platform.config.ICEServer;

import java.util.List;

/**
 * webrtc 通信服务
 *
 * @author
 */
public interface IWebrtcService {

    void call(Long uid, String mode,String offer);

    void accept(Long uid,  String answer);

    void reject(Long uid);

    void cancel(Long uid);

    void failed(Long uid, String reason);

    void handup(Long uid);

    void candidate(Long uid, String candidate);

    List<ICEServer> getIceServers();


}
