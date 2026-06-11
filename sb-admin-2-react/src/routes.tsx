import React from "react";
import {BrowserRouter, Routes, Route} from "react-router-dom";

// Pages
import SignUp from "./pages/SignUp";
import SignIn from "./pages/SignIn";
import Dashboard from "./pages/Dashboard";
import NotFound from "./pages/NotFound";
import Cards from "./pages/Cards";
import Charts from "./pages/Charts";
import Products from "./pages/Products";
import ProductDetail from "./pages/Products/Detail";
import Orders from "./pages/Orders";
import Users from "./pages/Users";

const AppRoutes: React.FC = () => (
    <BrowserRouter>
        <Routes>
            <Route path="/" element={<SignIn/>}/>
            <Route path="/dashboard" element={<Dashboard/>}/>
            <Route path="/signup" element={<SignUp/>}/>
            <Route path="/cards" element={<Cards/>}/>
            <Route path="/charts" element={<Charts/>}/>
            <Route path="/products" element={<Products/>}/>
            <Route path="/products/:id" element={<ProductDetail/>}/>
            <Route path="/orders" element={<Orders/>}/>
            <Route path="/users" element={<Users/>}/>
            <Route path="*" element={<NotFound/>}/>
        </Routes>
    </BrowserRouter>
);

export default AppRoutes;
