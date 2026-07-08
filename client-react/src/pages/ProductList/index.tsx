import React, { useEffect, useState } from 'react';
import { useSearchParams } from 'react-router-dom';
import ProductCard from '../../components/ProductCard';
import CategoryFilter from '../../components/CategoryFilter';
import { getProductsByCategory, Product } from '../../services/productService';

const CATEGORIES = ['전체', '신발', '가방', '의류', '잡화'];

const ProductList = () => {
    const [searchParams, setSearchParams] = useSearchParams();
    const selectedCategory = searchParams.get('category') ?? '전체';
    const [products, setProducts] = useState<Product[]>([]);

    useEffect(() => {
        getProductsByCategory(selectedCategory).then(setProducts);
    }, [selectedCategory]);

    const handleSelect = (category: string) => {
        setSearchParams(category === '전체' ? {} : { category });
    };

    return (
        <div>
            <h2 className="mb-4">상품 목록</h2>
            <CategoryFilter
                categories={CATEGORIES}
                selected={selectedCategory}
                onSelect={handleSelect}
            />
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
