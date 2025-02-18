package org.serious.dev.enums;

import lombok.Getter;

@Getter
public enum ImageExtension {
    JPEG("image/jpeg", ".jpg"),
    PNG("image/png", ".png"),
    GIF("image/gif", ".gif");

    private final String contentType;
    private final String extension;

    ImageExtension(String contentType, String imageExtension) {
        this.contentType = contentType;
        this.extension = imageExtension;
    }

    public static String getImageExtension(String contentType) {
        for (ImageExtension imageExtension : values()) {
            if (imageExtension.contentType.equals(contentType)) {
                return imageExtension.getExtension();
            }
        }
        return "";
    }
}
