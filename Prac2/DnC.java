import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.Optional;
import java.util.BinaryOperator;

class DnC<T,R> {

    private final T prob;
    private final Predicate<T> check;
    private final Function<T,R> fn;
    private final Function<T, Pair<T,T>> subfn;
    private final Supplier<T> supplier;

    private DnC(T prob, Predicate<T> check, Function<T,R> fn) {
        this.prob = prob;
        this.check = check;
        this.fn = fn;
        //this.subfn = Optional.<Function<T, Pair<T,T>>>empty();
    }

    private DnC(T prob, Predicate<T> check, Function<T,R> fn, Optional<Function<T, Pair<T,T>>> subfn) {
        this.prob = prob;
        this.check = check;
        this.fn = fn;
        this.subfn = subfn;
    }


    private DnC(T prob, Predicate<T> check, Function<T,R> fn, Optional<Function<T, Pair<T,T>>> subfn, Supplier<T> supplier) {
        this.prob = prob;
        this.check = check;
        this.fn = fn;
        this.subfn = subfn;
        this.supplier = supplier;
    }

    static <T,R> DnC<T,R> of(T prob, Predicate<T> check, Function<T,R> fn) {
        return new DnC<T,R>(prob, check, fn);
    }

    static <T,R> DnC<T,R> of(T prob, Predicate<T> check, Function<T,R> fn, Function<T,Pair<T,T>> subfn) { 
        return new DnC<T,R>(prob, check, fn, subfn);
    }


    static <T,R> DnC<T,R> of(T prob, Predicate<T> check, Function<T,R> fn, Function<T,Pair<T,T>> subfn, Supplier<T> supplier) { 
        return new DnC<T,R>(prob, check, fn, subfn, supplier);
    }

    void peek(Consumer<T> action) {
        action.accept(this.prob);
    }

    //check why orElse is not working
    //check supplier get() method and where else it has to be used + have i
    //used it correctly 
    protected Optional<R> solve () {
        Optional<T> res = Optional.<T>of(prob.get());
        return res.filter(check).map(fn).orElse(Optional.empty());
    }

    //fix recursion 
    protected Optional<R> solve(BinarOperator<R> bifn) {
        Optional<T> res = Optional.<T>of(prob);
        if (this.solve()) {
            return this.solve();
        } else {
            System.out.println("(res.first(),res.second()) evaluated");
            return res.flatMap(bifn(this.left().solve(bifn), this.right().solve(bifn)));
    }

    //p is an optional but we want object p unwrapped --> figure it out for
    //left() and right() 
    protected DnC<T,R> left() {
        Optional<Pair<T,T>> p = prob.flatMap(subfn);
        return DnC.<T,R>of(p.map(x -> x.first()), this.check, this.fn, this.subfn);
    }
    
    protected DnC<T,R> left() {
        Optional<Pair<T,T>> p = -> prob.flatMap(subfn);
        return DnC.<T,R>of(p.map(x -> x.second()), this.check, this.fn, this.subfn);
    }


}
