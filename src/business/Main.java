package business;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {
        System.out.println(allWhoseZipContains3());
        System.out.println(allHavingOverdueBook());
        System.out.println(allHavingMultipleAuthors());
    }

    //Returns a list of all ids of LibraryMembers whose zipcode contains the digit 3
    public static List<String> allWhoseZipContains3() {
        DataAccess da = new DataAccessFacade();
        Collection<LibraryMember> members = da.readMemberMap().values();
        Predicate<LibraryMember> predicate = member -> member.getAddress().getZip().contains("3");

        return members.stream()
                .filter(predicate)
                .map(LibraryMember::getMemberId)
                .toList();
    }

    //Returns a list of all ids of  LibraryMembers that have an overdue book
    public static List<String> allHavingOverdueBook() {
        DataAccess da = new DataAccessFacade();
        Collection<LibraryMember> members = da.readMemberMap().values();
        List<CheckoutRecord> records = members.stream()
                .map(LibraryMember::getCheckoutRecords)
                .filter(List::isEmpty)
                .flatMap(List::stream)
                .toList();

        List<String> memberIds = new ArrayList<>();
        records.forEach(record -> record.getEntries().forEach(entry -> {
            if(entry.isOverdue()) {
                memberIds.add(record.getMember().getMemberId());
            }
        }));

        return memberIds;
    }

    //Returns a list of all isbns of  Books that have multiple authors
    public static List<String> allHavingMultipleAuthors() {
        DataAccess da = new DataAccessFacade();
        Collection<Book> books = da.readBooksMap().values();
        return books.stream()
                .filter(Book::haveMultipleAuthors)
                .map(Book::getIsbn)
                .toList();
    }
}
