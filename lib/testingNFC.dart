import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_nfc_reader/flutter_nfc_reader.dart';
import 'package:geolocator/geolocator.dart';
import 'dart:async';

void main() => runApp(new MyHomePage());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return null;
  }
}

class MyHomePage extends StatefulWidget {
  final String title;

  MyHomePage({Key key, this.title}) : super(key: key);
  @override
  _MyHomePageState createState() => new _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  NfcData _nfcData;

  @override
  void initState() {
    super.initState();
  }

  ///Function starts NFC reading for the app
  ///Sets the state
  Future<void> startNFC() async {
    this.setState(() {
      _nfcData = NfcData();
      _nfcData.status = NFCStatus.reading;
    });

    debugPrint('NFC: Scan starting');
    FlutterNfcReader.read.listen((response) {
      setState(() {
        _nfcData = response;
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return null;
  }
}
