package Examples;

public class Decorator1 {
    public interface Cafe{
        double getPreco();
        String getDescricao();
    }
    public static class CafeSimples implements Cafe {
        @Override
        public String getDescricao() {
            return "café simples";
        };
        @Override
        public double getPreco() {
            return 4.00;
        };
    };

    public static class Capuccino implements Cafe{
        @Override
        public String getDescricao() {
            return "Capuccino";
        };
        @Override
        public double getPreco() {
            return 8.00;
        };

    }

    public static abstract class AdicionalDecorator implements Cafe{
        protected Cafe cafewrapped;

        public  AdicionalDecorator(Cafe cafe){
            this.cafewrapped = cafe;
        }
    }

    public static class Leite extends AdicionalDecorator {
        public Leite(Cafe cafe) {
            super(cafe);
        }

        @Override
        public double getPreco() {
            return cafewrapped.getPreco() + 3.00;
        }

        @Override
        public String getDescricao() {
            return cafewrapped.getDescricao() + ", leite";
        }
    }

        public static class Chantilly extends AdicionalDecorator {
            public Chantilly(Cafe cafe) {
                super(cafe);
            }

            @Override
            public double getPreco() {
                return cafewrapped.getPreco() + 2.00;
            }

            @Override
            public String getDescricao() {
                return cafewrapped.getDescricao() + " ,chantilly";
            }
        }



    public static void main(String[] args) {
        Cafe cafe = new Chantilly(new Leite(new CafeSimples()));
        System.out.println(cafe.getDescricao() + " = R$ " + cafe.getPreco());

    }
}
    