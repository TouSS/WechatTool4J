package xx.wechat.tools.utils;

/**
 * MIME
 */
public enum MIME {

    IMAGE_JPG("image/jpeg", "jpg"),
    IMAGE_JPEG("image/jpeg", "jpeg"),
    IMAGE_PNG("image/png", "png"),
    IMAGE_GIF("image/gif", "gif"),
    AUDIO_AMR("voice/amr", "amr"),
    AUDIO_MP3("audio/mp3", "mp3"),
    VIDEO_MP4("video/mp4", "mp4"),
    APPLICATION_OCTET_STREAM("application/octet-stream", ".");

    private String type;
    private String extension;

    MIME(String type, String extension) {
        this.type = type;
        this.extension = extension;
    }

    public String getType() {
        return type;
    }

    public String getExtension() {
        return extension;
    }

    public static MIME fromType(String type) {
        if (type != null) {
            type = type.trim().toLowerCase();
            for (MIME mime : MIME.values()) {
                if (mime.getType().equals(type)) {
                    return mime;
                }
            }
        }
        return MIME.APPLICATION_OCTET_STREAM;
    }


    public static MIME fromExtension(String extension) {
        if (extension != null) {
            extension = extension.trim().toLowerCase();
            for (MIME mime : MIME.values()) {
                if (mime.getExtension().equals(extension)) {
                    return mime;
                }
            }
        }
        return MIME.APPLICATION_OCTET_STREAM;
    }
}
