package hu.unideb.RFTDatingSite.Model;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class ChatList {

    Set<ChatMessage> chatMessageList = new Set<ChatMessage>() {

        /**
         * Returns the number of elements in this set (its cardinality).  If this
         * set contains more than <tt>Integer.MAX_VALUE</tt> elements, returns
         * <tt>Integer.MAX_VALUE</tt>.
         *
         * @return the number of elements in this set (its cardinality)
         */
        @Override
        public int size() {
            return 0;
        }

        /**
         * Returns <tt>true</tt> if this set contains no elements.
         *
         * @return <tt>true</tt> if this set contains no elements
         */
        @Override
        public boolean isEmpty() {
            return false;
        }

        /**
         * Returns <tt>true</tt> if this set contains the specified element.
         * More formally, returns <tt>true</tt> if and only if this set
         * contains an element <tt>e</tt> such that
         * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
         *
         * @param o element whose presence in this set is to be tested
         * @return <tt>true</tt> if this set contains the specified element
         * @throws ClassCastException   if the type of the specified element
         *                              is incompatible with this set
         *                              (<a href="Collection.html#optional-restrictions">optional</a>)
         * @throws NullPointerException if the specified element is null and this
         *                              set does not permit null elements
         *                              (<a href="Collection.html#optional-restrictions">optional</a>)
         */
        @Override
        public boolean contains(Object o) {
            return false;
        }

        /**
         * Returns an iterator over the elements in this set.  The elements are
         * returned in no particular order (unless this set is an instance of some
         * class that provides a guarantee).
         *
         * @return an iterator over the elements in this set
         */
        @Override
        public Iterator<ChatMessage> iterator() {
            return null;
        }

        /**
         * Performs the given action for each element of the {@code Iterable}
         * until all elements have been processed or the action throws an
         * exception.  Unless otherwise specified by the implementing class,
         * actions are performed in the order of iteration (if an iteration order
         * is specified).  Exceptions thrown by the action are relayed to the
         * caller.
         *
         * @param action The action to be performed for each element
         * @throws NullPointerException if the specified action is null
         * @implSpec <p>The default implementation behaves as if:
         * <pre>{@code
         *     for (T t : this)
         *         action.accept(t);
         * }</pre>
         * @since 1.8
         */
        @Override
        public void forEach(Consumer<? super ChatMessage> action) {

        }

        /**
         * Returns an array containing all of the elements in this set.
         * If this set makes any guarantees as to what order its elements
         * are returned by its iterator, this method must return the
         * elements in the same order.
         *
         * <p>The returned array will be "safe" in that no references to it
         * are maintained by this set.  (In other words, this method must
         * allocate a new array even if this set is backed by an array).
         * The caller is thus free to modify the returned array.
         *
         * <p>This method acts as bridge between array-based and collection-based
         * APIs.
         *
         * @return an array containing all the elements in this set
         */
        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        /**
         * Returns an array containing all of the elements in this set; the
         * runtime type of the returned array is that of the specified array.
         * If the set fits in the specified array, it is returned therein.
         * Otherwise, a new array is allocated with the runtime type of the
         * specified array and the size of this set.
         *
         * <p>If this set fits in the specified array with room to spare
         * (i.e., the array has more elements than this set), the element in
         * the array immediately following the end of the set is set to
         * <tt>null</tt>.  (This is useful in determining the length of this
         * set <i>only</i> if the caller knows that this set does not contain
         * any null elements.)
         *
         * <p>If this set makes any guarantees as to what order its elements
         * are returned by its iterator, this method must return the elements
         * in the same order.
         *
         * <p>Like the {@link #toArray()} method, this method acts as bridge between
         * array-based and collection-based APIs.  Further, this method allows
         * precise control over the runtime type of the output array, and may,
         * under certain circumstances, be used to save allocation costs.
         *
         * <p>Suppose <tt>x</tt> is a set known to contain only strings.
         * The following code can be used to dump the set into a newly allocated
         * array of <tt>String</tt>:
         *
         * <pre>
         *     String[] y = x.toArray(new String[0]);</pre>
         * <p>
         * Note that <tt>toArray(new Object[0])</tt> is identical in function to
         * <tt>toArray()</tt>.
         *
         * @param a the array into which the elements of this set are to be
         *          stored, if it is big enough; otherwise, a new array of the same
         *          runtime type is allocated for this purpose.
         * @return an array containing all the elements in this set
         * @throws ArrayStoreException  if the runtime type of the specified array
         *                              is not a supertype of the runtime type of every element in this
         *                              set
         * @throws NullPointerException if the specified array is null
         */
        @Override
        public <T> T[] toArray(T[] a) {
            return null;
        }

        /**
         * Adds the specified element to this set if it is not already present
         * (optional operation).  More formally, adds the specified element
         * <tt>e</tt> to this set if the set contains no element <tt>e2</tt>
         * such that
         * <tt>(e==null&nbsp;?&nbsp;e2==null&nbsp;:&nbsp;e.equals(e2))</tt>.
         * If this set already contains the element, the call leaves the set
         * unchanged and returns <tt>false</tt>.  In combination with the
         * restriction on constructors, this ensures that sets never contain
         * duplicate elements.
         *
         * <p>The stipulation above does not imply that sets must accept all
         * elements; sets may refuse to add any particular element, including
         * <tt>null</tt>, and throw an exception, as described in the
         * specification for {@link Collection#add Collection.add}.
         * Individual set implementations should clearly document any
         * restrictions on the elements that they may contain.
         *
         * @param chatMessage element to be added to this set
         * @return <tt>true</tt> if this set did not already contain the specified
         * element
         * @throws UnsupportedOperationException if the <tt>add</tt> operation
         *                                       is not supported by this set
         * @throws ClassCastException            if the class of the specified element
         *                                       prevents it from being added to this set
         * @throws NullPointerException          if the specified element is null and this
         *                                       set does not permit null elements
         * @throws IllegalArgumentException      if some property of the specified element
         *                                       prevents it from being added to this set
         */
        @Override
        public boolean add(ChatMessage chatMessage) {
            return false;
        }

        /**
         * Removes the specified element from this set if it is present
         * (optional operation).  More formally, removes an element <tt>e</tt>
         * such that
         * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>, if
         * this set contains such an element.  Returns <tt>true</tt> if this set
         * contained the element (or equivalently, if this set changed as a
         * result of the call).  (This set will not contain the element once the
         * call returns.)
         *
         * @param o object to be removed from this set, if present
         * @return <tt>true</tt> if this set contained the specified element
         * @throws ClassCastException            if the type of the specified element
         *                                       is incompatible with this set
         *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
         * @throws NullPointerException          if the specified element is null and this
         *                                       set does not permit null elements
         *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
         * @throws UnsupportedOperationException if the <tt>remove</tt> operation
         *                                       is not supported by this set
         */
        @Override
        public boolean remove(Object o) {
            return false;
        }

        /**
         * Returns <tt>true</tt> if this set contains all of the elements of the
         * specified collection.  If the specified collection is also a set, this
         * method returns <tt>true</tt> if it is a <i>subset</i> of this set.
         *
         * @param c collection to be checked for containment in this set
         * @return <tt>true</tt> if this set contains all of the elements of the
         * specified collection
         * @throws ClassCastException   if the types of one or more elements
         *                              in the specified collection are incompatible with this
         *                              set
         *                              (<a href="Collection.html#optional-restrictions">optional</a>)
         * @throws NullPointerException if the specified collection contains one
         *                              or more null elements and this set does not permit null
         *                              elements
         *                              (<a href="Collection.html#optional-restrictions">optional</a>),
         *                              or if the specified collection is null
         * @see #contains(Object)
         */
        @Override
        public boolean containsAll(Collection<?> c) {
            return false;
        }

        /**
         * Adds all of the elements in the specified collection to this set if
         * they're not already present (optional operation).  If the specified
         * collection is also a set, the <tt>addAll</tt> operation effectively
         * modifies this set so that its value is the <i>union</i> of the two
         * sets.  The behavior of this operation is undefined if the specified
         * collection is modified while the operation is in progress.
         *
         * @param c collection containing elements to be added to this set
         * @return <tt>true</tt> if this set changed as a result of the call
         * @throws UnsupportedOperationException if the <tt>addAll</tt> operation
         *                                       is not supported by this set
         * @throws ClassCastException            if the class of an element of the
         *                                       specified collection prevents it from being added to this set
         * @throws NullPointerException          if the specified collection contains one
         *                                       or more null elements and this set does not permit null
         *                                       elements, or if the specified collection is null
         * @throws IllegalArgumentException      if some property of an element of the
         *                                       specified collection prevents it from being added to this set
         * @see #add(Object)
         */
        @Override
        public boolean addAll(Collection<? extends ChatMessage> c) {
            return false;
        }

        /**
         * Retains only the elements in this set that are contained in the
         * specified collection (optional operation).  In other words, removes
         * from this set all of its elements that are not contained in the
         * specified collection.  If the specified collection is also a set, this
         * operation effectively modifies this set so that its value is the
         * <i>intersection</i> of the two sets.
         *
         * @param c collection containing elements to be retained in this set
         * @return <tt>true</tt> if this set changed as a result of the call
         * @throws UnsupportedOperationException if the <tt>retainAll</tt> operation
         *                                       is not supported by this set
         * @throws ClassCastException            if the class of an element of this set
         *                                       is incompatible with the specified collection
         *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
         * @throws NullPointerException          if this set contains a null element and the
         *                                       specified collection does not permit null elements
         *                                       (<a href="Collection.html#optional-restrictions">optional</a>),
         *                                       or if the specified collection is null
         * @see #remove(Object)
         */
        @Override
        public boolean retainAll(Collection<?> c) {
            return false;
        }

        /**
         * Removes from this set all of its elements that are contained in the
         * specified collection (optional operation).  If the specified
         * collection is also a set, this operation effectively modifies this
         * set so that its value is the <i>asymmetric set difference</i> of
         * the two sets.
         *
         * @param c collection containing elements to be removed from this set
         * @return <tt>true</tt> if this set changed as a result of the call
         * @throws UnsupportedOperationException if the <tt>removeAll</tt> operation
         *                                       is not supported by this set
         * @throws ClassCastException            if the class of an element of this set
         *                                       is incompatible with the specified collection
         *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
         * @throws NullPointerException          if this set contains a null element and the
         *                                       specified collection does not permit null elements
         *                                       (<a href="Collection.html#optional-restrictions">optional</a>),
         *                                       or if the specified collection is null
         * @see #remove(Object)
         * @see #contains(Object)
         */
        @Override
        public boolean removeAll(Collection<?> c) {
            return false;
        }

        /**
         * Removes all of the elements of this collection that satisfy the given
         * predicate.  Errors or runtime exceptions thrown during iteration or by
         * the predicate are relayed to the caller.
         *
         * @param filter a predicate which returns {@code true} for elements to be
         *               removed
         * @return {@code true} if any elements were removed
         * @throws NullPointerException          if the specified filter is null
         * @throws UnsupportedOperationException if elements cannot be removed
         *                                       from this collection.  Implementations may throw this exception if a
         *                                       matching element cannot be removed or if, in general, removal is not
         *                                       supported.
         * @implSpec The default implementation traverses all elements of the collection using
         * its {@link #iterator}.  Each matching element is removed using
         * {@link Iterator#remove()}.  If the collection's iterator does not
         * support removal then an {@code UnsupportedOperationException} will be
         * thrown on the first matching element.
         * @since 1.8
         */
        @Override
        public boolean removeIf(Predicate<? super ChatMessage> filter) {
            return false;
        }

        /**
         * Removes all of the elements from this set (optional operation).
         * The set will be empty after this call returns.
         *
         * @throws UnsupportedOperationException if the <tt>clear</tt> method
         *                                       is not supported by this set
         */
        @Override
        public void clear() {

        }

        /**
         * Creates a {@code Spliterator} over the elements in this set.
         *
         * <p>The {@code Spliterator} reports {@link Spliterator#DISTINCT}.
         * Implementations should document the reporting of additional
         * characteristic values.
         *
         * @return a {@code Spliterator} over the elements in this set
         * @implSpec The default implementation creates a
         * <em><a href="Spliterator.html#binding">late-binding</a></em> spliterator
         * from the set's {@code Iterator}.  The spliterator inherits the
         * <em>fail-fast</em> properties of the set's iterator.
         * <p>
         * The created {@code Spliterator} additionally reports
         * {@link Spliterator#SIZED}.
         * @implNote The created {@code Spliterator} additionally reports
         * {@link Spliterator#SUBSIZED}.
         * @since 1.8
         */
        @Override
        public Spliterator<ChatMessage> spliterator() {
            return null;
        }

        /**
         * Returns a sequential {@code Stream} with this collection as its source.
         *
         * <p>This method should be overridden when the {@link #spliterator()}
         * method cannot return a spliterator that is {@code IMMUTABLE},
         * {@code CONCURRENT}, or <em>late-binding</em>. (See {@link #spliterator()}
         * for details.)
         *
         * @return a sequential {@code Stream} over the elements in this collection
         * @implSpec The default implementation creates a sequential {@code Stream} from the
         * collection's {@code Spliterator}.
         * @since 1.8
         */
        @Override
        public Stream<ChatMessage> stream() {
            return null;
        }

        /**
         * Returns a possibly parallel {@code Stream} with this collection as its
         * source.  It is allowable for this method to return a sequential stream.
         *
         * <p>This method should be overridden when the {@link #spliterator()}
         * method cannot return a spliterator that is {@code IMMUTABLE},
         * {@code CONCURRENT}, or <em>late-binding</em>. (See {@link #spliterator()}
         * for details.)
         *
         * @return a possibly parallel {@code Stream} over the elements in this
         * collection
         * @implSpec The default implementation creates a parallel {@code Stream} from the
         * collection's {@code Spliterator}.
         * @since 1.8
         */
        @Override
        public Stream<ChatMessage> parallelStream() {
            return null;
        }
    };
}
