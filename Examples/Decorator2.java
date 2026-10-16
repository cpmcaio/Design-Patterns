package Examples;

public class Decorator2 {
    public interface Produto {
        double getPreco();
        String getNome();
    }

    public static class Camisa implements Produto {
        @Override
        public String getNome() {
            return "camisa";
        };
        @Override
        public double getPreco() {
            return 10.00;
        };
    };
    public static class ProdutoDecorator implements Produto {
        protected Produto produtoWrapped;

        public ProdutoDecorator(Produto produto) {
            this.produtoWrapped = produto;
        };

        @Override
        public double getPreco() {
            return produtoWrapped.getPreco();
        };

        @Override
        public String getNome() {
            return produtoWrapped.getNome();
        };
    };
    
    public static class Serigrafia extends ProdutoDecorator {
        public Serigrafia(Produto produto) {
            super(produto);
        };

        @Override
        public double getPreco() {
            return produtoWrapped.getPreco() + 5.00;
        };

        @Override
        public String getNome() {
            return produtoWrapped.getNome() + ", serigrafia";
        };
    };
    
    public static class Bordado extends ProdutoDecorator {
        public Bordado(Produto produto) {
            super(produto);
        };

        @Override
        public double getPreco() {
            return produtoWrapped.getPreco() + 10.00;
        };

        @Override
        public String getNome() {
            return produtoWrapped.getNome() + ", bordado";
        };
    };

    public static void main(String[] args) {
        Produto produto = new Camisa();
        System.out.println(produto.getNome() + " - " + produto.getPreco());

        produto = new Serigrafia(produto);
        System.out.println(produto.getNome() + " - " + produto.getPreco());

        produto = new Bordado(produto);
        System.out.println(produto.getNome() + " - " + produto.getPreco());
    };
}