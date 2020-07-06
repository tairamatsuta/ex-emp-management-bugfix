package q4;

/**
 * 絵柄を表す列挙型。
 *
 * @author y.morinaga
 */
public enum Suit {

    /**
     * スペード.
     */
    SPADE("スペード"),
    /**
     * ハート.
     */
    HEART("ハート"),
    /**
     * クラブ.
     */
    CLUB("クラブ"),
    /**
     * ダイヤ.
     */
    DIAMOND("ダイヤ"),
    ;

    private final String text;
    private Suit(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
