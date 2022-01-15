package AlgoritmiaI.examenes.paricalI;

import AlgoritmiaI.datastructures.binarytree.List;
import AlgoritmiaI.datastructures.binarytree.Node;

public final class Inventory {
    private final List<Product> editingProducts;

    public Inventory(Product[] initialAvailableProducts) {
        this.editingProducts = new List<>();

        fillEditingProductsList(initialAvailableProducts);
    }

    private void fillEditingProductsList(Product[] initialAvailableProducts) {
        for (Product product : initialAvailableProducts) {
            this.editingProducts.insert(product, -1);
        }
    }

    public Product getProductById(int id) {
        Node<Product> current = editingProducts.getFirst();

        while (current != null){
            if(current.getData().getId() == id){
                return current.getData();
            }

            current = current.getNext();
        }

        return null;
    }

    public void addNewProduct(Product newProduct) {
        checkAlreadyExists(newProduct);

        this.editingProducts.insert(newProduct, -1);
    }

    private void checkAlreadyExists(Product newProduct){
        if(getProductById(newProduct.getId()) != null){
            throw new ProductExistsException();
        }
    }

    public boolean removeOneProduct(long id){
        Node<Product> current = editingProducts.getFirst();
        int currentIndex = 0;

        while (current != null){
            if(current.getData().getId() == id){
                this.editingProducts.remove(currentIndex);

                return true;
            }

            currentIndex++;
        }

        return false;
    }

    public Product[] getProducts(){
        return (Product[]) editingProducts.listData();
    }
}
