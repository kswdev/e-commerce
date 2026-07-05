import React from 'react';
import { Link } from 'react-router-dom';
import { useAuthStore } from '../../../store/authStore';
import { useCartStore } from '../../../store/cartStore';

const Header: React.FC = () => {
    const { isLoggedIn, username, logout } = useAuthStore();
    const itemCount = useCartStore(state => state.items.reduce((sum, i) => sum + i.quantity, 0));

    return (
        <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
            <div className="container">
                <Link className="navbar-brand" to="/">ShopApp</Link>
                <div className="navbar-nav ms-auto align-items-center">
                    <Link className="nav-link" to="/">상품목록</Link>
                    <Link className="nav-link" to="/cart">
                        장바구니
                        {itemCount > 0 && (
                            <span className="badge bg-danger ms-1">{itemCount}</span>
                        )}
                    </Link>
                    {isLoggedIn ? (
                        <>
                            <span className="nav-link text-light">{username}님</span>
                            <button className="btn btn-outline-light btn-sm ms-2" onClick={logout}>로그아웃</button>
                        </>
                    ) : (
                        <Link className="nav-link" to="/login">로그인</Link>
                    )}
                </div>
            </div>
        </nav>
    );
};

export default Header;
