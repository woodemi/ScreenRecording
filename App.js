/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

import React, { Component } from 'react';
import {
  NativeModules,
  StyleSheet,
  Button,
  Text,
  View,
  TouchableOpacity
} from 'react-native';

export const { start, stop } = NativeModules.RNInterractModule;

export default class App extends Component {
 
  constructor(props) {
     super(props);
     this.state = {
       text:'Welcome to React Native!',
       isTouchDown:false
      };
   }

  render() {

    // var定义变量，根据状态值改变对应值
    var welcomeText = this.state.text;
    var bgcolor;
    if (this.state.isTouchDown) {
      bgcolor = '#c5c5ab';
    } else {
      bgcolor = '#F5FCFF';
    }
    console.log('testtststststts');

    // 返回的即界面显示内容
    // onPress={() => this.touchDown()，下面onPress这样写也可以
    return (
      <View style={[styles.container, {backgroundColor: bgcolor}]}>
        <Text style={styles.welcome}>
          {welcomeText}
        </Text>
        <Text style={styles.instructions}>
          To get started, edit index.ios.js
        </Text>
        <Text style={styles.instructions}>
          Press Cmd+R to reload,{'\n'}
          Cmd+D or shake for dev menu
        </Text>
        <TouchableOpacity onPress={this.touchDown.bind(this)}>
          <Text style={[styles.instructions, {backgroundColor: 'green'}]}>
            test touch Me
          </Text>
        </TouchableOpacity>
        <TouchableOpacity onPress={this.startBtn.bind(this)}>
          <Text style={[styles.btns, {backgroundColor: 'red'}]}>
            start record
          </Text>
        </TouchableOpacity>

        <TouchableOpacity onPress={this.stopBtn.bind(this)}>
          <Text style={[styles.btns, {backgroundColor: 'cyan'}]}>
            stop record
          </Text>
        </TouchableOpacity>
      </View>
    );
  }

  startBtn() {
    console.log('startBtn');
    start('RN调用原生弹出的提示 start');
  }

  stopBtn() {
    console.log('stopBtn');
    stop('RN调用原生弹出的提示 stop', (path) => alert(path));
  }

  // 自定义函数
  touchDown() {
    //  console.log 控制台打印，可打印值，多用于调试
    console.log('>>', this.isTouchDown);

    if (!this.state.isTouchDown) {
      this.setState({
        text:'Test Touch Down Success',
        isTouchDown:true
      });
    } else {
      this.setState({
        text:'Test Touch Down Again Success',
        isTouchDown:false
      });
    }
  }
}

// 定义样式
const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
  btns: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
    fontSize: 20,
  },
});
