package q4;

/**
 * ランクを表す列挙型。
 *
 * @author y.morinaga
 */
public enum Rank {

    ACE(1, "A"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    JACK(11, "J"),
    QUEEN(12, "Q"),
    KING(13, "K");

    private final Integer number;
    private final String text;

    private Rank(final Integer number, final String text) {
        this.number = number;
        this.text = text;
    }

    public Integer getNumberAsIs() {
        return number;
    }

    public String getText() {
        return text;
    }

}
