package brickset;

import repository.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a repository of {@code LegoSet} objects.
 */
public class LegoSetRepository extends Repository<LegoSet> {

    public LegoSetRepository() {
        super(LegoSet.class, "brickset.json");
    }

    /**
     * Prints the number of LEGO sets with the tag specified.
     *
     * @param tag a LEGO set tag
     */
    public void printLegoSetsWithTagCount(String tag) {
        System.out.println("Number of Lego sets with tag \"" + tag + "\" : " + getAll().stream()
                .filter(legoSet -> legoSet.getTags() != null && legoSet.getTags().contains(tag))
                .count());
    }

    /**
     * Prints the name of a LEGO set with the number specified.
     *
     * @param number a LEGO set number
     */
    public void printLegoSetNameByNumber(String number) {
        getAll().stream()
                .filter(legoSet -> legoSet.getNumber().equals(number))
                .map(LegoSet::getName)
                .forEach(System.out::println);
    }

    /**
     * Returns the number of pieces of the LEGO set with the most pieces.
     *
     * @return the number of pieces in the LEGO set
     */
    public long getLegoSetWithMostPieces() {
        return getAll().stream()
                .mapToLong(LegoSet::getPieces)
                .max()
                .getAsLong();
    }

    /**
     * Returns a list containing the names of the LEGO sets with the given theme
     *
     * @param theme a LEGO set theme
     * @return a list containing the names of the LEGO sets with the given theme
     */
    public List<String> getLegoSetsNamesByThemes(String theme) {
        return getAll().stream()
                .filter(legoSet -> legoSet.getTheme().equals(theme))
                .map(LegoSet::getName)
                .collect(Collectors.toList());
    }

    /**
     * Returns a list containing the names of the LEGO sets with pieces less than the given number
     *
     * @param pieces a LEGO set pieces
     * @return a list containing the names of the LEGO sets
     */
    public List<String> getLegoSetsNamesWithPiecesLessThan(int pieces) {
        return getAll().stream()
                .filter(legoSet -> legoSet.getPieces() < pieces)
                .map(LegoSet::getName)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {

        var repository = new LegoSetRepository();

        repository.printLegoSetsWithTagCount("Car");

        repository.printLegoSetNameByNumber("3836");

        System.out.println(repository.getLegoSetWithMostPieces());

        System.out.println(repository.getLegoSetsNamesByThemes("Games"));

        System.out.println(repository.getLegoSetsNamesWithPiecesLessThan(150));
    }

}
