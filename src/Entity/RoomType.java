package Entity;

public enum RoomType {
    Single("Single"), Doulbe("Double"), Queen("Queen"), Quad("Quad"), Triple("Triple");
    private String type;

    RoomType() {
        ;
    }

    RoomType(String type) {
        this.type = type;
    }

    private String getType() {
        return type;
    }
}
