/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ducnh.stickerstory;

import java.io.IOException;
import java.util.*;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author ducnh
 */
@ServerEndpoint(
    value = "/story/notifications", 
    encoders = {StickerEncoder.class},
    decoders = {StickerDecoder.class})
public class StoryWebSocket {
    private static final List<Sticker> stickers = Collections.synchronizedList(new LinkedList<Sticker>());
    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());
    
    @OnMessage
    public void onMessage(Session session, Sticker sticker) {
        stickers.add(sticker);
        for (Session openSession : sessions) {
            try {
                openSession.getBasicRemote().sendObject(sticker);
            } catch (IOException | EncodeException ex) {
                sessions.remove(openSession);
            }
        }
    }
    
    @OnOpen
    public void onOpen(Session session) throws IOException, EncodeException {
        sessions.add(session);
        for (Sticker sticker : stickers) {
            session.getBasicRemote().sendObject(sticker);
        }
    }
    
    @OnClose
    public void onClose(Session session) throws IOException, EncodeException {
        sessions.remove(session);
    }
}
