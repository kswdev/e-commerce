import React from 'react';
import { useCartStore } from '../../store/cartStore';

const Cart = () => {
    const { items, removeItem, updateQuantity, totalPrice, clearCart } = useCartStore();

    if (items.length === 0) {
        return (
            <div className="text-center py-5">
                <h2>장바구니</h2>
                <p className="text-muted">장바구니가 비어있습니다.</p>
            </div>
        );
    }

    return (
        <div>
            <h2 className="mb-4">장바구니</h2>
            <table className="table">
                <thead>
                    <tr>
                        <th>상품명</th>
                        <th>가격</th>
                        <th>수량</th>
                        <th>소계</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    {items.map(item => (
                        <tr key={item.id}>
                            <td>{item.name}</td>
                            <td>{item.price.toLocaleString()}원</td>
                            <td>
                                <input
                                    type="number"
                                    min={1}
                                    value={item.quantity}
                                    onChange={e => updateQuantity(item.id, Number(e.target.value))}
                                    className="form-control"
                                    style={{ width: 70 }}
                                />
                            </td>
                            <td>{(item.price * item.quantity).toLocaleString()}원</td>
                            <td>
                                <button className="btn btn-sm btn-outline-danger" onClick={() => removeItem(item.id)}>
                                    삭제
                                </button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <div className="text-end">
                <p className="fs-5 fw-bold">합계: {totalPrice().toLocaleString()}원</p>
                <button className="btn btn-outline-secondary me-2" onClick={clearCart}>전체 삭제</button>
                <button className="btn btn-primary">주문하기</button>
            </div>
        </div>
    );
};

export default Cart;
