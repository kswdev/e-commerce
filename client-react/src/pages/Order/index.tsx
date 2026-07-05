import React from 'react';
import { useCartStore } from '../../store/cartStore';

const Order = () => {
    const items = useCartStore(state => state.items);
    const totalPrice = useCartStore(state => state.totalPrice);

    return (
        <div>
            <h2 className="mb-4">주문 내역</h2>
            {items.length === 0 ? (
                <p className="text-muted">주문 내역이 없습니다.</p>
            ) : (
                <ul className="list-group">
                    {items.map(item => (
                        <li key={item.id} className="list-group-item d-flex justify-content-between">
                            <span>{item.name} × {item.quantity}</span>
                            <span>{(item.price * item.quantity).toLocaleString()}원</span>
                        </li>
                    ))}
                    <li className="list-group-item d-flex justify-content-between fw-bold">
                        <span>합계</span>
                        <span>{totalPrice().toLocaleString()}원</span>
                    </li>
                </ul>
            )}
        </div>
    );
};

export default Order;
