import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Flutter Demo',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.green, background: Color.fromRGBO(195, 255, 199,  10)),

        useMaterial3: true,
      ),
      home: const MyHomePage(title:'TRUCADOS'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  int _counter = 0;
  int _counter2 = 0;

  void _incrementCounter() {
    setState(() {
      _counter = _counter + 3;
    });
  }

  void _incrementCounter2() {
    setState(() {
      _counter2 = _counter2 + 3;
    });
  }

  void _resetCounters() {
    setState(() {
      _counter = 0;
      _counter2 = 0;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.primary,
        title: Text(widget.title, style: TextStyle(fontSize: 23, fontWeight: FontWeight.bold, color: Colors.lightGreenAccent)),
      ),
      body: Column(
        children: [
          Container(
            margin: EdgeInsets.only(top:40, left:40, right:40),
              child: Text('TIME 1- placar da partida', style: TextStyle(fontSize: 25, color: Colors.green, fontWeight: FontWeight.bold) ),
          ),
          Container(
              child: ElevatedButton(
                onPressed: _incrementCounter, child: null,
                style: ButtonStyle(alignment: Alignment.center, ),
              ),
          ),
        ],
      ),
    );
  }

}


