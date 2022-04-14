package brickset;

import repository.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Represents a repository of {@code LegoSet} objects.
 */
public class LegoSetRepository extends Repository<LegoSet> {

    public LegoSetRepository() {
        super(LegoSet.class, "brickset.json");
    }

//        WORKING WITH STREAMS (2) HW BEGINS HERE

    /**
     * 1.Returns boolean if there are sets that contain the specified stringElement within their name
     * @param stringElement to be checked within the name of the LegoSet
     * @return boolean if there are sets that contain the stringElement within the set name
     */
    public boolean boolSetsWithNameContainingStringElement(String stringElement) {
        return getAll().stream()
                .anyMatch(legoSet -> legoSet.getName().contains(stringElement));
    }

    /**
     * 2.Prints all the tags within the set number after being sorted
     * @param number of the set
     */
    public void printsAllTheTagsWithinTheSetNumberAfterBeingSorted(String number) {
        getAll().stream()
                .filter(legoset -> legoset.getNumber().equals(number))
                .flatMap(legoset -> legoset.getTags().stream()).sorted().forEach(System.out::println);
    }

    /**
     * 3.Returns the longest string of the tags of a set within a given name
     * @param name of the set
     * @return longest string of the tags contained in paranthesis []
     */
    public Optional<String> returnLongestStringOfTheTagsWithinAGivenName(String name) {
        return getAll().stream()
                .filter(legoset -> legoset.getName().equals(name))
                .flatMap(legoset -> legoset.getTags().stream())
                .distinct().reduce((firstWord, secondWord) -> firstWord.length() > secondWord.length() ? firstWord : secondWord);

    }

    /**
     * 4.Returns a map containing the number of a legoset and its name provided the pieces are above the given number specified
     * @param number of pieces
     * @return A Map consiting of a number - name pair values
     */
    public Map<String, String> returnMapWithNumberAndNameOfLegoSetWithMoreThanGiveThanNumberOfPieces(int number) {
        return getAll().stream()
                .filter(legoSet -> legoSet.getPieces() > number)
                .collect(Collectors.toMap(LegoSet::getNumber, LegoSet::getName));
    }

    /**
     * 5.Returns a map containing all of the packaging types and the names of the legoSet that are within them
     * @return Map (Subthemes : Name of Legoset Pair)
     */
    public Map<PackagingType, List<String>> returnMapPackagingTypeWithNamesListWithinLegoSet() {
        return getAll().stream()
                .collect(Collectors.groupingBy(LegoSet::getPackagingType,
                        Collectors.mapping(LegoSet::getName, Collectors.toList())));
    }


//    /** Prints all legosets with the packaging type which does not equal NONE and NOT_SPECIFIED **/
//    public void printLegosetsForPackagingTypeWithoutNoneAndNotSpecified() {
//        getAll().stream()
//                .filter(packagingType -> packagingType.getPackagingType() != PackagingType.NONE && packagingType.getPackagingType() != PackagingType.NOT_SPECIFIED).forEach(System.out::println);
//    }
//
//    /** Prints the first 5 legosets as they appear normally based on number **/
//    public void printNameOfFirst5Legosets() {
//        getAll().stream().
//                limit(5).
//                forEach(System.out::println);
//    }
//
//    /** Returns the number of legosets with the theme as Duplo **/
//    public long NumberOfLegosetsWithThemeDuplo() {
//        return getAll().stream().
//                filter(name -> name.getTheme().contains("Duplo")).
//                count();
//    }
//
//    /** Returns the maximum number of legosets according to their pieces **/
//    public  long themaximumNumberOfLegoSetPieces(){
//        return   getAll().stream().mapToLong(LegoSet::getPieces).max().getAsLong();
//    }
//
//    /** Prints all themes that start with B in ascending order **/
//    public void printThemesStartWithBInReverseOrder() {
//        getAll().stream()
//                .map(LegoSet::getTheme)
//                .filter(legoset -> legoset.startsWith("B"))
//                .distinct()
//                .sorted(Comparator.nullsFirst(Comparator.reverseOrder()))
//                .forEach(System.out::println);
//    }


    public static void main(String[] args) {
        var repository = new LegoSetRepository();



//        WORKING WITH STREAMS (2)

        System.out.println("1.Returns boolean if there are sets that contain the specified stringElement within their name");
        System.out.println(repository.boolSetsWithNameContainingStringElement("Heart"));

        System.out.println("2.Prints all the tags within the set number after being sorted");
        repository.printsAllTheTagsWithinTheSetNumberAfterBeingSorted("MCDR5");

        System.out.println("3.Returns the longest string of the tags of a set within a given name");
        System.out.println(repository.returnLongestStringOfTheTagsWithinAGivenName("Lap Star"));

        System.out.println("4.Returns a map containing the number of a legoset and its name provided the pieces are above the given number specified");
        System.out.println(repository.returnMapWithNumberAndNameOfLegoSetWithMoreThanGiveThanNumberOfPieces(49));

        System.out.println("5.Returns a map containing all of the packaging types and the names of the legoSet that are within them");
        System.out.println(repository.returnMapPackagingTypeWithNamesListWithinLegoSet());



//        /** 1) Prints all legosets with the packaging type which does not equal NONE and NOT_SPECIFIED **/
//        repository.printLegosetsForPackagingTypeWithoutNoneAndNotSpecified();
//
//        /** 2) Prints the first 5 legosets as they appear normally based on number **/
//        repository.printNameOfFirst5Legosets();
//
//        /** 3) Returns the number of legosets with the theme as Duplo **/
//        System.out.println(repository.NumberOfLegosetsWithThemeDuplo());
//
//        /** 4) Returns the maximum number of legosets according to their pieces **/
//        System.out.println(repository.themaximumNumberOfLegoSetPieces());
//
//        /** 5) Prints all themes that start with B in ascending order **/
//        repository.printThemesStartWithBInReverseOrder();
    }

}
