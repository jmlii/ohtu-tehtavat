
package statistics;

import statistics.matcher.*;


class QueryBuilder {
    
   Matcher matcher;
    
    public QueryBuilder() {
        matcher = new All();
    }
    
    public Matcher build() {
        Matcher m = matcher;
        matcher = new LikeMatcher();
        return m;
    }
    
    public QueryBuilder playsIn(String team) {
        this.matcher = new And (matcher, new PlaysIn(team));
        return this;
    }
    
    public QueryBuilder hasAtLeast(int value, String category) {
        this.matcher = new And (matcher, new HasAtLeast(value, category));
        return this;
    }
    
    public QueryBuilder hasFewerThan (int value, String category) {
        this.matcher = new And (matcher, new HasFewerThan(value, category));
        return this;
    }

    public QueryBuilder oneOf(Matcher... matchers) {
        this.matcher = new Or(matchers);
        return this;
    }

    
    private static class LikeMatcher implements Matcher {
        public LikeMatcher() {
        }
        @Override
        public boolean matches(Player p) {
            return true;
        }
    }
    
}
