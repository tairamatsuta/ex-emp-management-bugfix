package q4;

/**
 * カードクラス。
 *
 * @author y.morinaga
 */
public class Card implements ICard {

    /**
     * カードの絵柄。
     */
    private final Suit suit;

    /**
     * カードのランク。
     */
    private final Rank rank;

    /**
     * コンストラクタ。
     *
     * @param suit
     *            絵柄
     * @param rank
     *            ランク
     */
    public Card(final Suit suit, final Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    /* (non-Javadoc)
     * @see javaoo.ICard#getSuit()
     */
    @Override
    public Suit getSuit() {
        return suit;
    }

    /* (non-Javadoc)
     * @see javaoo.ICard#getRank()
     */
    @Override
    public Rank getRank() {
        return rank;
    }

    /* (non-Javadoc)
     * @see javaoo.ICard#isRed()
     */
    public boolean isRed() {
        return Suit.HEART.equals(suit)
                || Suit.DIAMOND.equals(suit);
    }

    /* (non-Javadoc)
     * @see javaoo.ICard#isBlack()
     */
    public boolean isBlack() {
        return Suit.SPADE.equals(suit)
                || Suit.CLUB.equals(suit);
    }

    /* (non-Javadoc)
     * @see javaoo.ICard#isJoker()
     */
    public boolean isJoker() {
        return false;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.format("%sの%s", suit.getText(), rank.getText());
    }

}
