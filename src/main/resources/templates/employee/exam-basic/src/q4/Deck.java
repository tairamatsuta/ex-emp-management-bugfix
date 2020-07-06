package q4;

import java.util.ArrayList;
import java.util.List;

/**
 * カードのデッキを表すクラス。
 *
 * @author y.morinaga
 */
public class Deck {

    /**
     * デッキに含まれるカードのリスト.
     */
    private List<ICard> cards;

    /**
     * コンストラクタ。
     * 全ての絵柄の全てのランクを含む、新規デッキを作成する。
     */
    public Deck() {
        cards = new ArrayList<ICard>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    public List<ICard> getCards() {
        return cards;
    }

}
