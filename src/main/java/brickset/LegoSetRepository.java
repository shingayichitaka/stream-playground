package brickset;

import repository.Repository;

import javax.xml.namespace.QName;
import java.util.Comparator;

/**
 * Represents a repository of {@code LegoSet} objects.
 */
public class LegoSetRepository extends Repository<LegoSet> {

    public LegoSetRepository() {
        super(LegoSet.class, "brickset.json");
    }

    /** Prints all legosets with the packaging type which does not equal NONE and NOT_SPECIFIED **/
    public void printLegosetsForPackagingTypeWithoutNoneAndNotSpecified() {
        getAll().stream()
                .filter(packagingType -> packagingType.getPackagingType() != PackagingType.NONE && packagingType.getPackagingType() != PackagingType.NOT_SPECIFIED).forEach(System.out::println);
    }

    /** Prints the first 5 legosets as they appear normally based on number **/
    public void printNameOfFirst5Legosets() {
        getAll().stream().
                limit(5).
                forEach(System.out::println);
    }

    /** Returns the number of legosets with the theme as Duplo **/
    public long NumberOfLegosetsWithThemeDuplo() {
        return getAll().stream().
                filter(name -> name.getTheme().contains("Duplo")).
                count();
    }

    /** Returns the maximum number of legosets according to their pieces **/
    public  long themaximumNumberOfLegoSetPieces(){
        return   getAll().stream().mapToLong(LegoSet::getPieces).max().getAsLong();
    }

    /** Prints all themes that start with B in ascending order **/
    public void printThemesStartWithBInReverseOrder() {
        getAll().stream()
                .map(LegoSet::getTheme)
                .filter(legoset -> legoset.startsWith("B"))
                .distinct()
                .sorted(Comparator.nullsFirst(Comparator.reverseOrder()))
                .forEach(System.out::println);
    }


    public static void main(String[] args) {
        var repository = new LegoSetRepository();

        /** 1) Prints all legosets with the packaging type which does not equal NONE and NOT_SPECIFIED **/
        repository.printLegosetsForPackagingTypeWithoutNoneAndNotSpecified();

        /** 2) Prints the first 5 legosets as they appear normally based on number **/
        repository.printNameOfFirst5Legosets();

        /** 3) Returns the number of legosets with the theme as Duplo **/
        System.out.println(repository.NumberOfLegosetsWithThemeDuplo());

        /** 4) Returns the maximum number of legosets according to their pieces **/
        System.out.println(repository.themaximumNumberOfLegoSetPieces());

        /** 5) Prints all themes that start with B in ascending order **/
        repository.printThemesStartWithBInReverseOrder();

    }

}
