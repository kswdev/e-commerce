import React from 'react';
import { Link } from 'react-router-dom';

interface ProductCardProps {
    id: number;
    name: string;
    price: number;
    imageUrl: string;
}

const ProductCard: React.FC<ProductCardProps> = ({ id, name, price, imageUrl }) => {
    return (
        <div className="card h-100">
            <img src={imageUrl} className="card-img-top" alt={name} />
            <div className="card-body d-flex flex-column">
                <h5 className="card-title">{name}</h5>
                <p className="card-text text-muted">{price.toLocaleString()}원</p>
                <Link to={`/products/${id}`} className="btn btn-primary mt-auto">
                    상세보기
                </Link>
            </div>
        </div>
    );
};

export default ProductCard;
