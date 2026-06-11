import React, { Component } from 'react';

interface Props {
  title?: string;
  children?: React.ReactNode;
}

interface State {
  title: string;
}

class CardBasic extends Component<Props, State> {
  constructor(props: Props) {
    super(props);
    this.state = { title: '' };
  }

  componentDidMount() {
    this.setState({ title: this.props.title ?? 'Basic Card Example' });
  }

  render() {
    return (
      <div className="card shadow mb-4">
        <div className="card-header py-3">
          <h6 className="m-0 font-weight-bold text-primary">{this.state.title}</h6>
        </div>
        <div className="card-body">
          {this.props.children}
        </div>
      </div>
    );
  }
}

export default CardBasic;
