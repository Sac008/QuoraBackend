package com.project.Quora.user;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.UUID;

public class HexToUUIdConvertor extends JsonDeserializer<UUID> {


    public UUID deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {


        String hexString = jsonParser.getText();

        if (!isValidHexUUID(hexString)) {
            throw new IllegalArgumentException("UHUHUHUHUHHHHUHUHUH");
        }

        if (hexString.startsWith("0x")) {
            hexString = hexString.substring(2); // Remove the "0x" prefix
        }

        return HexToUUIdConvertor.hexToUUID(hexString);
    }

    public static UUID hexToUUID(String hex) {
        // Remove the "0x" prefix if present
        if (hex.startsWith("0x")) {
            hex = hex.substring(2);
        }

        // Ensure the hex string is of length 32 (16 bytes for a UUID)
        if (hex.length() != 32) {
            throw new IllegalArgumentException("Invalid hex string length for UUID");
        }

        // Parse the first and second halves of the hex string as long values
        long mostSigBits = Long.parseUnsignedLong(hex.substring(0, 16), 16);
        long leastSigBits = Long.parseUnsignedLong(hex.substring(16), 16);

        // Create and return a UUID
        return new UUID(mostSigBits, leastSigBits);
    }

    public boolean isValidHexUUID(String hexUUID) {
        String regex = "^0x[0-9a-fA-F]{32}$";
        return hexUUID.matches(regex);
    }
}
