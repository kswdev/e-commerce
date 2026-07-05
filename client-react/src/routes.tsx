import React from "react";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Blank from "./pages/Blank";

// Pages


const AppRoutes: React.FC = () => (
    <BrowserRouter>
        <Routes>
            <Route path="/" element={<Blank/>}/>
        </Routes>
    </BrowserRouter>
);

export default AppRoutes;
