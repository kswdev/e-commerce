import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { getProductById, Product } from '../../services/productService';
import { useCartStore } from '../../store/cartStore';

const ProductDetail = () => {
    const { id } = useParams<{ id: string }>();
    const [product, setProduct] = useState<Product | null>(null);
    const addItem = useCartStore(state => state.addItem);

    useEffect(() => {
        getProductById(Number(id)).then(p => setProduct(p ?? null));
    }, [id]);

    if (!product) {
        return <p>상품을 찾을 수 없습니다.</p>;
    }

    return (
        <div className="row">
            <div className="col-md-6">
                <img src={product.imageUrl} alt={product.name} className="img-fluid rounded" />
            </div>
            <div className="col-md-6">
                <h2>{product.name}</h2>
                <p className="text-muted fs-5">{product.price.toLocaleString()}원</p>
                <p>{product.description}</p>
                <button
                    className="btn btn-primary"
                    onClick={() => addItem({ id: product.id, name: product.name, price: product.price })}
                >
                    장바구니 담기
                </button>
            </div>
        </div>
    );
};

export default ProductDetail;
