import React, { Component } from 'react';

interface Props {
  title?: string;
  children?: React.ReactNode;
}

interface State {
  title: string;
}

class CardDefault extends Component<Props, State> {
  constructor(props: Props) {
    super(props);
    this.state = { title: '' };
  }

  componentDidMount() {
    this.setState({ title: this.props.title ?? 'Default Card Example' });
  }

  render() {
    return (
      <div className="card mb-4">
        <div className="card-header">
          {this.state.title}
        </div>
        <div className="card-body">
          {this.props.children}
        </div>
      </div>
    );
  }
}

export default CardDefault;
