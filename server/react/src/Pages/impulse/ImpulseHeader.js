import React from 'react'
import { Descriptions } from 'antd'
import { Table } from 'antd';
import { Tag, Space, Col, Row, Button } from "antd";
import './impulse.css'

function ImpulseHeader() {

  const { Column, ColumnGroup } = Table;
  const columns = [
    {
      title: 'Name',
      dataIndex: 'name',
      key: 'name',
      render: text => <a>{text}</a>,
    },
    {
      title: 'Age',
      dataIndex: 'age',
      key: 'age',
    },
    {
      title: 'Address',
      dataIndex: 'address',
      key: 'address',
    },
    {
      title: 'Tags',
      key: 'tags',
      dataIndex: 'tags',
      render: tags => (
        <>
          {tags.map(tag => {
            let color = tag.length > 5 ? 'geekblue' : 'green';
            if (tag === 'loser') {
              color = 'volcano';
            }
            return (
              <Tag color={color} key={tag}>
                {tag.toUpperCase()}
              </Tag>
            );
          })}
        </>
      ),
    },
    {
      title: 'Action',
      key: 'action',
      render: (text, record) => (
        <Space size="middle">
          <a>Invite {record.name}</a>
          <a>Delete</a>
        </Space>
      ),
    },
  ];

  const data = [
    {
      key: '1',
      name: 'John Brown',
      age: 32,
      address: 'New York No. 1 Lake Park',
      tags: ['nice', 'developer'],
    },
    {
      key: '2',
      name: 'Jim Green',
      age: 42,
      address: 'London No. 1 Lake Park',
      tags: ['loser'],
    },
    {
      key: '3',
      name: 'Joe Black',
      age: 32,
      address: 'Sidney No. 1 Lake Park',
      tags: ['cool', 'teacher'],
    },
  ];

  const data1 = [
    {
      key: '1',
      firstName: 'John',
      lastName: 'Brown',
      age: 32,
      address: 'New York No. 1 Lake Park',
      tags: ['nice', 'developer'],
    },
    {
      key: '2',
      firstName: 'Jim',
      lastName: 'Green',
      age: 42,
      address: 'London No. 1 Lake Park',
      tags: ['loser'],
    },
    {
      key: '3',
      firstName: 'Joe',
      lastName: 'Black',
      age: 32,
      address: 'Sidney No. 1 Lake Park',
      tags: ['cool', 'teacher'],
    },
  ];


  return (
    <React.Fragment>
      <h3 className="page-header">Impulse Test Results</h3>
      <div className="page-header-container">
        <h3>Transformer Details</h3>
      </div>
      <div className="box-container">
        <Descriptions>
          <Descriptions.Item label="Customer">GULF POWER COMPANY</Descriptions.Item>
          <Descriptions.Item label="TR No">WT05561</Descriptions.Item>
          <Descriptions.Item label="Test">ANSI</Descriptions.Item>
          <Descriptions.Item label="Tech">1323</Descriptions.Item>
          <Descriptions.Item label="SERIAL NO"></Descriptions.Item>
          <Descriptions.Item label="HV WINDING:">115.50 kV</Descriptions.Item>
          <Descriptions.Item>±2 of 2.750 kV Taps</Descriptions.Item>
          <Descriptions.Item label="BIL">450 kV</Descriptions.Item>
        </Descriptions>
      </div>
      <div className="page-header-container">
        <h3>BIL Information</h3>
      </div>
      <div className="box-container">
        <Table dataSource={data} pagination={false}>
          <ColumnGroup title="HV Line">
            <Column title="FW" dataIndex="firstName" key="firstName" />
            <Column title="CW" dataIndex="lastName" key="lastName" />
            <Column title="SW/SS" dataIndex="lastName" key="lastName" />
          </ColumnGroup>
          <ColumnGroup title="LV Line">
            <Column title="FW" dataIndex="firstName" key="firstName" />
            <Column title="CW" dataIndex="lastName" key="lastName" />
            <Column title="SW" dataIndex="lastName" key="lastName" />
          </ColumnGroup>
          <ColumnGroup title="TV Line">
            <Column title="FW" dataIndex="firstName" key="firstName" />
            <Column title="CW" dataIndex="lastName" key="lastName" />
            <Column title="SW" dataIndex="lastName" key="lastName" />
          </ColumnGroup>
          <ColumnGroup title="Neutral">
            <Column title="H0X0" dataIndex="firstName" key="firstName" />
            <Column title="" dataIndex="lastName" key="lastName" />
            <Column title="Y0" dataIndex="lastName" key="lastName" />
          </ColumnGroup>

        </Table>
      </div>

      <div className="page-header-container">
        <h3>Impulse Generator Setup</h3>
      </div>
      <div className="box-container ant-table-content ant-table">
        <table>
          <tr>
            <th >Gen</th>
            <td >1</td>
            <td >2</td>
            <td >3</td>
            <td >4</td>
            <td >5</td>
            <td >6</td>
            <td >7</td>
            <td >8</td>
            <td >9</td>
            <td >10</td>
          </tr>
          <tr>
            <th>Stages in Parallel</th>
            <td>10</td>
            <td>10</td>
            <td >3</td>
            <td >1</td>
            <td >1</td>
            <td >10</td>
            <td >3</td>
            <td ></td>
            <td ></td>
            <td ></td>

          </tr>
          <tr>
            <th >Stages in Series</th>
            <td >1</td>
            <td >1</td>
            <td >3</td>
            <td >3</td>
            <td >4</td>
            <td >1</td>
            <td >3</td>
            <td ></td>
            <td ></td>
            <td ></td>

          </tr>
          <tr>
            <th >R1 (front)</th>
            <td >8-2800,2-1400</td>
            <td >7-10,3-22</td>
            <td >9-30</td>
            <td >3-10</td>
            <td >3-10.2-10/10</td>
            <td >8-2.8k,2-1.4k</td>
            <td >9-30</td>
            <td ></td>
            <td ></td>
            <td ></td>

          </tr>
          <tr>
            <th >R1 (tail)</th>
            <td >6-2200,4-440</td>
            <td >7-92,3-160</td>
            <td >9-440</td>
            <td >3-92</td>
            <td >4-2200</td>
            <td >7-2.2k,3-440</td>
            <td >7-440.2-92</td>
            <td ></td>
            <td ></td>
            <td ></td>

          </tr>
        </table>
      </div>

    </React.Fragment>
  )
}

export default ImpulseHeader

