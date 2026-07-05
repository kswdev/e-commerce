import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuthStore } from '../../store/authStore';

const Login = () => {
    const [username, setUsername] = useState('');
    const login = useAuthStore(state => state.login);
    const navigate = useNavigate();

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        if (username.trim()) {
            login(username.trim());
            navigate('/');
        }
    };

    return (
        <div className="row justify-content-center">
            <div className="col-md-4">
                <h2 className="mb-4">로그인</h2>
                <form onSubmit={handleSubmit}>
                    <div className="mb-3">
                        <label className="form-label">이름</label>
                        <input
                            type="text"
                            className="form-control"
                            value={username}
                            onChange={e => setUsername(e.target.value)}
                            placeholder="이름을 입력하세요"
                        />
                    </div>
                    <button type="submit" className="btn btn-primary w-100">로그인</button>
                </form>
            </div>
        </div>
    );
};

export default Login;
