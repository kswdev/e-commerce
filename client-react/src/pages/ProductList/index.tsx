import React, { useEffect, useState } from 'react';
import { useSearchParams } from 'react-router-dom';
import ProductCard from '../../components/ProductCard';
import CategoryFilter from '../../components/CategoryFilter';
import SearchBar from '../../components/SearchBar';
import { getProductsByCategory, searchProductsByName, Product } from '../../services/productService';
import { fetchCategories } from '../../api/categoryApi';

const ProductList = () => {
    const [searchParams, setSearchParams] = useSearchParams();
    const selectedCategory = searchParams.get('category') ?? '전체';
    const searchQuery = searchParams.get('q') ?? '';

    const [categories, setCategories] = useState<string[]>(['전체']);
    const [categoryProducts, setCategoryProducts] = useState<Product[]>([]);

    useEffect(() => {
        fetchCategories().then(cats => setCategories(['전체', ...cats]));
    }, []);

    useEffect(() => {
        getProductsByCategory(selectedCategory).then(setCategoryProducts);
    }, [selectedCategory]);

    const visibleProducts = searchProductsByName(categoryProducts, searchQuery);

    const handleCategorySelect = (category: string) => {
        setSearchParams(prev => {
            const next = new URLSearchParams(prev);
            category === '전체' ? next.delete('category') : next.set('category', category);
            return next;
        });
    };

    const handleSearchChange = (query: string) => {
        setSearchParams(prev => {
            const next = new URLSearchParams(prev);
            query ? next.set('q', query) : next.delete('q');
            return next;
        });
    };

    return (
        <div>
            <h2 className="mb-4">상품 목록</h2>
            <SearchBar value={searchQuery} onChange={handleSearchChange} />
            <CategoryFilter
                categories={categories}
                selected={selectedCategory}
                onSelect={handleCategorySelect}
            />
            {visibleProducts.length === 0 ? (
                <div className="text-center text-muted py-5">
                    <p>검색 결과가 없습니다.</p>
                    <p>다른 검색어나 카테고리를 시도해 보세요.</p>
                </div>
            ) : (
                <div className="row row-cols-1 row-cols-md-3 g-4">
                    {visibleProducts.map(product => (
                        <div className="col" key={product.id}>
                            <ProductCard {...product} />
                        </div>
                    ))}
                </div>
            )}
        </div>
    );
};

export default ProductList;
