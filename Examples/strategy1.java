package Examples;

public class strategy1 {
    public interface RouteStrategyImp {
        void buildRoute(String A, String B);
    }

    public static class Navigator {
        private final RouteStrategyImp routeStrategy;

        public Navigator(RouteStrategyImp assignedRouteStrategy) {
            this.routeStrategy = assignedRouteStrategy;
        }

        public void buildRoute(String A, String B) {
            routeStrategy.buildRoute(A, B);
        }
    }

    public static class RoadStrategy implements RouteStrategyImp {
        public void buildRoute(String A, String B) {
            System.out.printf("Traced Route %s FROM %s on Road%n", A, B);
        }
    }

    public static class PublicTransport implements RouteStrategyImp {
        public void buildRoute(String A, String B) {
            System.out.printf("Traced Route %s FROM %s on public transport%n", A, B);
        }
    }

    public static void main(String[] args) {
        PublicTransport publictransport = new PublicTransport();
        Navigator navigator = new Navigator(publictransport);

        navigator.buildRoute("João Pessoa", "Caruaru"); 
    }
    
}
