/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ducnh.stickerstory;

import java.io.IOException;
import java.io.Reader;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.spi.JsonProvider;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author ducnh
 */
public class StickerDecoder implements Decoder.TextStream<Sticker> {

    @Override
    public Sticker decode(Reader reader) throws DecodeException, IOException {
        JsonProvider provider = JsonProvider.provider();
        JsonReader jsonReader = provider.createReader(reader);
        JsonObject jsonSticker = jsonReader.readObject();
        Sticker sticker = new Sticker();
        sticker.setX(jsonSticker.getInt("x"));
        sticker.setY(jsonSticker.getInt("y"));
        sticker.setImage(jsonSticker.getString("sticker"));
        return sticker;
    }

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }
}
