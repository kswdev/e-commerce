import { fetchProducts, fetchProductById } from '../api/productApi';

export interface Product {
    id: number;
    name: string;
    price: number;
    imageUrl: string;
    description: string;
    category: string;
}

export const getProductById = async (id: number): Promise<Product | undefined> => {
    return fetchProductById(id);
};

export const getProductsByCategory = async (category: string): Promise<Product[]> => {
    const products = await fetchProducts();
    if (category === '전체') return products;
    return products.filter(p => p.category === category);
};

export const searchProductsByName = (products: Product[], query: string): Product[] => {
    const normalized = query.trim().toLowerCase();
    if (!normalized) return products;
    return products.filter(p => p.name.toLowerCase().includes(normalized));
};
