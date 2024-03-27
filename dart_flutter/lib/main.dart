import 'dart:ffi';
import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp()); // chama a função runApp com o widget MyApp como parâmetro
}

class MyApp extends StatelessWidget { // MyApp é uma espécie de widget que não muda seu estado
  const MyApp({super.key});

  // esse widget é a raíz da aplicação
  @override
  Widget build(BuildContext context) { // widget construtor
    return MaterialApp(
      debugShowCheckedModeBanner: false, // remove a faixa de debug do app
      title: 'Flutter Demo',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.green, background: const Color.fromRGBO(195, 255, 199,  1)), // Definindo esquema de cores
        useMaterial3: true,
      ),
      home: const MyHomePage(title:'TRUCADOS'), // Título do App na pagina inicial MyHoomePage
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {  // Classe responsável por observar o código e mudar o estado caso necessário
  int _counter = 0; // contador time 1
  int _counter2 = 0; // contador time 2
  int _counterRodada1 = 0; // contador de rodadada do time 1
  int _counterRodada2 = 0; // contador de rodada do time 2

  void _incrementCounter() {  // incrementa em 1 o contador do time 1
    setState(() {
      _counter += 1;
    });

    if (_counter>=12){  // caso o contador seja maior ou igual a 12
      setState(() {
        _counterRodada1 += 1; // incrementa 1 no contador de rodada
        _counter = 0; // reseta os outros contadores
        _counter2 = 0;
      });
    }
  }

  void _incrementThreeCounter() { // incrementa em 3 o contador do time 1
    setState(() {
      _counter += 3;
    });

    if (_counter>=12){ // caso o contador seja maior ou igual a 12
      setState(() {
        _counterRodada1 += 1; // incrementa o contador de rodada do time 1
        _counter = 0; // reseta os outros contadores
        _counter2 = 0;
      });
    }
  }

  void _incrementCounter2() { // incrementa em 1 o contador do time 2
    setState(() {
      _counter2 += 1;
    });
    if (_counter2>=12){ // se o contador do time 2 for maior ou igual a 12
      setState(() {
        _counterRodada2 += 1; // incrementa o contador de rodada do time 2
        _counter2 = 0; // reseta os contadores
        _counter = 0;
      });
    }
  }

  void _incrementThreeCounter2() { // incrementa em 3 o contador do time 2
    setState(() {
      _counter2 += 3;
    });

    if (_counter2>=12){ // se o contador do time 2 for maior ou igual a 12
      setState(() {
        _counterRodada2 += 1;  // incrementa o contador de rodada do time 2
        _counter = 0;  // reseta os outros contadores
        _counter2 = 0;
      });
    }
  }

  void _resetCounters() { // reseta todos os contadores
    setState(() {
      _counter = 0;
      _counter2 = 0;
      _counterRodada1 = 0;
      _counterRodada2 = 0;
    });
  }

  @override
  Widget build(BuildContext context) { //widget construtor do MyApp
    return Scaffold( // cria a estrutura da visual básica da tela principal
      appBar: AppBar( // componente AppBar
        backgroundColor: Theme.of(context).colorScheme.primary,
        title: Text(widget.title, style: const TextStyle(fontSize: 23, fontWeight: FontWeight.bold, color: Colors.lightGreenAccent)), //modifica o estilo de texto do título
      ),
      body: Column( // O corpo do app será uma coluna, ou seja, todos os filho desse componente serão organizados de cima pra baixo
        children: [ // O widget possui um vetor de filhos, cada filho é um widget
          Container( // Widget container
            margin: const EdgeInsets.only(top:20, left:40, right:40), // Usado para definir as margens e os limites do container, os filhos desse container estão subordinados á esses limites
            child: const Text('PLACAR FINAL', style: TextStyle(fontSize: 26, color: Color.fromRGBO(14, 61, 0, 1), fontWeight: FontWeight.bold)), // texto com modificações
          ),

          Container(
            margin: const EdgeInsets.only(top:10, left: 40, right:40),
            child: Row( // dentro desse container há uma linha, ou seja, os filhops desse componente serão organizados horizontalmente, respeitando os limites do container
              mainAxisAlignment: MainAxisAlignment.spaceEvenly, // alinhamento no eixo proncipal definido para deixar os componentes da linha com espaçamento igualmente distribuidos entre si, como um gap no CSS
              children: [
                Column(
                  children: [
                    const Text('NÓS', style: TextStyle(fontSize: 25, color: Colors.green, fontWeight: FontWeight.bold) ), // texto do time 1
                    Text(
                      '$_counterRodada1',style: const TextStyle(fontWeight: FontWeight.bold, fontSize: 30, color: Color.fromRGBO(29, 67, 0, 0.8)),), // o contador de rodada do time 1 está sendo representado como texto nesse componente, podendo ser estilizado
                  ],
                ),
                Column(
                  children: [
                    const Text('ELES', style: TextStyle(fontSize: 25, color: Colors.green, fontWeight: FontWeight.bold) ), // texto do time 2
                    Text(
                      '$_counterRodada2', style: const TextStyle(fontWeight: FontWeight.bold, fontSize: 30, color: Color.fromRGBO(29, 67, 0, 0.8)), // contador de rodada do time 2, também representado como texto estilizado
                    ),
                  ],
                ),
              ],
            ),
          ),

          Container(
            margin: const EdgeInsets.only(top:30),
              child: const Text('NÓS - Placar da partida', style: TextStyle(fontSize: 25, color: Colors.green, fontWeight: FontWeight.bold) ),
          ),

          Text(
            '$_counter', style: const TextStyle(fontWeight: FontWeight.bold, fontSize: 40, color: Color.fromRGBO(29, 67, 0, 0.8)), // contador do time 1, representao como texto
          ),
        Row(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: [
            ElevatedButton( // widget de botão elevado, posui profundidade, diferente de outros botões
              onPressed: _incrementThreeCounter,
                style: ElevatedButton.styleFrom(
                    backgroundColor: const Color.fromRGBO(14, 63, 5, 0.8),
                    padding: const EdgeInsets.symmetric(horizontal: 20, vertical: 10),
                    textStyle: const TextStyle(fontSize: 20, fontWeight: FontWeight.bold)),
              child: const Text('+3', style: TextStyle(color: Color.fromRGBO(59, 255, 53,1)))
            ),
            ElevatedButton(
              onPressed: _incrementCounter,
                style: ElevatedButton.styleFrom(
                    backgroundColor: const Color.fromRGBO(14, 63, 5, 0.8),
                    padding: const EdgeInsets.symmetric(horizontal: 20, vertical: 10),
                    textStyle: const TextStyle(fontSize: 20, fontWeight: FontWeight.bold)),
              child: const Text('+1', style: TextStyle(color: Color.fromRGBO(59, 255, 53,1)))
            ),
          ]
        ),


          Container(
            margin: const EdgeInsets.only(top:40),
            child: const Text('ELES - Placar da partida', style: TextStyle(fontSize: 25, color: Colors.green, fontWeight: FontWeight.bold) ),
          ),

          Text(
              '$_counter2', style: const TextStyle(fontWeight: FontWeight.bold, fontSize: 40, color: Color.fromRGBO(29, 67, 0, 0.8)),
          ),

          Row(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: [
                ElevatedButton(
                    onPressed: _incrementThreeCounter2,
                    style: ElevatedButton.styleFrom(
                        backgroundColor: const Color.fromRGBO(14, 63, 5, 0.8),
                        padding: const EdgeInsets.symmetric(horizontal: 20, vertical: 10),
                        textStyle: const TextStyle(fontSize: 20, fontWeight: FontWeight.bold)),
                    child: const Text('+3', style: TextStyle(color: Color.fromRGBO(59, 255, 53,1)))
                ),
                ElevatedButton(
                    onPressed: _incrementCounter2,
                    style: ElevatedButton.styleFrom(
                        backgroundColor: const Color.fromRGBO(14, 63, 5, 0.8),
                        padding: const EdgeInsets.symmetric(horizontal: 20, vertical: 10),
                        textStyle: const TextStyle(fontSize: 20, fontWeight: FontWeight.bold)),
                    child: const Text('+1' , style: TextStyle(color: Color.fromRGBO(59, 255, 53,1)))
                ),
              ]
          ),

          Container(
            margin: const EdgeInsets.only(top:40),
            child: ElevatedButton(
                onPressed: _resetCounters,
                style: ElevatedButton.styleFrom(
                    backgroundColor: Colors.green,
                    padding: const EdgeInsets.symmetric(horizontal: 30, vertical: 15),
                    textStyle: const TextStyle(fontSize: 25, fontWeight: FontWeight.bold)),
                child: const Text('Recomecar')),
          ),

        ],
      ),

    );

  }
}


