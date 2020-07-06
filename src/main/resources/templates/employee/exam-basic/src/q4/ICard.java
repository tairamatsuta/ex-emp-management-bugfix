package q4;


/**
 * カードインタフェース。
 *
 * @author y.morinaga
 */
public interface ICard {

    /**
     * カードの絵柄を取得する。
     *
     * @return 絵柄.
     */
    Suit getSuit();

    /**
     * カードのランクを取得する。
     *
     * @return ランク.
     */
    Rank getRank();

}
