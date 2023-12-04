package business;

import java.util.Collection;
import java.util.List;

public interface ControllerInterface {
	void login(String id, String password) throws LoginException;

	void checkBook(String memberId, String isbn) throws LibrarySystemException;

	List<String> allMemberIds();

	List<String> allBookIds();

	void saveMember(LibraryMember member);

	Collection<LibraryMember> alLibraryMembers();

	void deleteMember(String memberId);

	LibraryMember getLibraryMemberById(String memberId);

	Collection<Book> allBooks();

	List<CheckoutHistory> getCheckoutHistory();

	Book getBookByISBN(String isbn);

	void saveBook(Book book);

	LibraryMember findMemberById(String memberId);
}
