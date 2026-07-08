import productsData from '../../data/products.json';

export interface Product {
    id: number;
    name: string;
    price: number;
    imageUrl: string;
    description: string;
    category: string;
}

// 추후 실제 API로 교체 시 이 파일만 수정하면 됨
// ex) return axios.get('/api/products').then(res => res.data)

export const getProducts = async (): Promise<Product[]> => {
    return productsData;
};

export const getProductById = async (id: number): Promise<Product | undefined> => {
    return productsData.find(p => p.id === id);
};

export const getProductsByCategory = async (category: string): Promise<Product[]> => {
    if (category === '전체') return productsData;
    return productsData.filter(p => p.category === category);
};
