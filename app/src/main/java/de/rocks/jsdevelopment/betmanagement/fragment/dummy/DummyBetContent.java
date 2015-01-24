package de.rocks.jsdevelopment.betmanagement.fragment.dummy;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.rocks.jsdevelopment.betmanagement.model.Bet;
import de.rocks.jsdevelopment.betmanagement.model.BetSubscriber;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyBetContent {

    /**
     * An array of sample (dummy) items.
     */
    //public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    public static List<Bet> ITEMS = new ArrayList<Bet>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, Bet> ITEM_MAP = new HashMap<String, Bet>();

    static {
        // Add 4 sample items.
        for (int i = 1; i < 15; i++) {
            addItem(createBet(i));
        }
    }


    private static Bet createBet(int i) {
        Bet bet = new Bet();
        bet.setName("Testwette" + i);
        bet.setDescription("Dies ist eine Testwette " + i);
        bet.setOwner("1");
        bet.setId(""+i);

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        try {
            bet.setDeadline(sdf.parse("21.01.2015"));
            bet.setStart(sdf.parse("23.01.2015"));
            bet.setEnd(sdf.parse("25.01.2015"));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        List<BetSubscriber> user = new ArrayList<BetSubscriber>();

        for (int pers = 1; pers < 5; pers++) {
            user.add(createUser(pers));
        }

        bet.setSubscriber(user);

        return bet;
    }

    private static BetSubscriber createUser(int pers) {
        BetSubscriber user = new BetSubscriber();

        user.setId(""+ pers);
        user.setName("Benutzer" + pers);
        user.setMail("mail" + pers + "@qweqwe.de");
        user.setTel("" + pers + pers + pers + pers);

        return user;
    }

    private static void addItem(Bet item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getId(), item);
    }

}
