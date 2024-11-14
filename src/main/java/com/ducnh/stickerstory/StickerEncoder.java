/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ducnh.stickerstory;

import java.io.IOException;
import java.io.Writer;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.json.spi.JsonProvider;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author ducnh
 */
public class StickerEncoder implements Encoder.TextStream<Sticker> {
    @Override
    public void encode(Sticker sticker, Writer writer) throws EncodeException, IOException {
        JsonProvider provider = JsonProvider.provider();
        JsonObject jsonSticker = provider.createObjectBuilder()
                .add("action", "add")
                .add("x", sticker.getX())
                .add("y", sticker.getY())
                .add("sticker", sticker.getImage())
                .build();
        try (JsonWriter jsonWriter = provider.createWriter(writer)) {
            jsonWriter.write(jsonSticker);
        }
    }

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }
}
