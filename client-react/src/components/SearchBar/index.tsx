import React from 'react';

interface Props {
    value: string;
    onChange: (query: string) => void;
    placeholder?: string;
}

const SearchBar: React.FC<Props> = ({ value, onChange, placeholder = '상품명 검색...' }) => (
    <div className="mb-4">
        <div className="input-group">
            <input
                type="search"
                className="form-control"
                value={value}
                onChange={e => onChange(e.target.value)}
                placeholder={placeholder}
                aria-label="상품 검색"
            />
            {value && (
                <button
                    className="btn btn-outline-secondary"
                    type="button"
                    onClick={() => onChange('')}
                    aria-label="검색 초기화"
                >
                    &times;
                </button>
            )}
        </div>
    </div>
);

export default SearchBar;
