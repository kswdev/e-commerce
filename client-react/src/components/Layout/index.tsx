import React from 'react';
import { Outlet } from 'react-router-dom';
import Header from '../Navigation/Header';
import Footer from '../Navigation/Footer';

const Layout: React.FC = () => {
    return (
        <div className="d-flex flex-column min-vh-100">
            <Header />
            <main className="container py-4 flex-grow-1">
                <Outlet />
            </main>
            <Footer />
        </div>
    );
};

export default Layout;
