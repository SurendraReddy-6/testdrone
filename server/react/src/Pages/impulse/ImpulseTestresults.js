import React from 'react'
import { Table } from 'antd';
import { Button, Col, Row } from "antd";


function ImpulseTestresults() {

  const Impulsedata = []

  const columns = [
    {
      title: 'Shots #',
      dataIndex: '874',
      key: 'name',
      fixed: 'left',
    },
    {
      title: 'File Name',
      dataIndex: 'X0RFW',
      key: 'age',
      fixed: 'left',
    },
    {
      title: 'Surge',
      dataIndex: 'address',
      key: '1',
    },
    {
      title: 'Ground',
      dataIndex: 'address',
      key: '2',
    },
    {
      title: 'Current',
      dataIndex: 'address',
      key: '3',
    },
    {
      title: 'HV Tap',
      dataIndex: 'address',
      key: '4',
    },
    {
      title: 'LV Tap',
      dataIndex: 'address',
      key: '5',
    },
    {
      title: 'Tie Together',
      dataIndex: 'address',
      key: '6',
      width: 100
    },
    {
      title: 'Divider Multiplier',
      dataIndex: 'address',
      key: '7',
      width: 100

    },
    { title: 'Up', dataIndex: 'address', key: '8' },
    { title: 'T1', dataIndex: 'address', key: '8' },
    { title: 'T2', dataIndex: 'address', key: '8' },
    { title: 'TC', dataIndex: 'address', key: '8' },
    { title: 'S', dataIndex: 'address', key: '8' },
    { title: 'TP', dataIndex: 'address', key: '8' },
    { title: 'TD', dataIndex: 'address', key: '8' },
    { title: 'TO', dataIndex: 'address', key: '8' },
    {
      title: 'Comment',
      key: 'operation',
      fixed: 'right',
      width: 120,
      render: () => <a>action</a>,
    },
  ];

  for (let i = 0; i < 3; i++) {
    Impulsedata.push({
      key: i,
      name: `XORFW`,
      age: 32,
      address: `${i}`,
    });
  }


  return (
    <React.Fragment>
        <div className="page-header-container">
          <h3>Impulse Test Results</h3>
          <div>
            <Button
              type="primary"
              style={{ marginRight: "15px" }}
            >
              Download
            </Button>
            <Button
              type="primary"
              
            >
              Gen Test Rep
            </Button>
          </div>
        </div>
      <div className='box-container'>
        <Table columns={columns} dataSource={Impulsedata} scroll={{ x: 1500, y: 300 }} />
      </div>
    </React.Fragment>
  )
}

export default ImpulseTestresults
