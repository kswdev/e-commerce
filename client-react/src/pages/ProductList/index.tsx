import React, { useEffect, useState } from 'react';
import ProductCard from '../../components/ProductCard';
import { getProducts, Product } from '../../services/productService';

const ProductList = () => {
    const [products, setProducts] = useState<Product[]>([]);

    useEffect(() => {
        getProducts().then(setProducts);
    }, []);

    return (
        <div>
            <h2 className="mb-4">상품 목록</h2>
            <div className="row row-cols-1 row-cols-md-3 g-4">
                {products.map(product => (
                    <div className="col" key={product.id}>
                        <ProductCard {...product} />
                    </div>
                ))}
            </div>
        </div>
    );
};

export default ProductList;
