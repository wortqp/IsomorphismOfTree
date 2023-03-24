=======
# 1. Введение
## 1.1 Глоссарий
- `Матрица смежности` - это квадратная матрица, в которой каждый элемент принимает одно из двух значений: `0` или `1`, где 1 - наличие ребра (связи) между узлами, а 0 - отсутствие. 
- `Подвешенное дерево` - это ориентированный граф без циклов, в котором в каждую вершину, кроме одной, называемой корнем ориентированного дерева, входит одно ребро.
- `Некорневое дерево` - это ориентированный граф без циклов, который не содержит корня и отражает связь листьев без предполагаемого положения общего предка.

## 1.2 Формальная постановка задачи
1. Изучить алгоритм "Изоморфизм деревьев" и описать его в форме научного доклада.
2. Реализовать алгоритм определения изоморфности деревьев.
4. Результат работы выложить на github.

## 1.3 Вступление
**Изоморфизм** - логико-математическое понятие, выражающее одинаковость строения (структуры) систем (процессов, конструкций).
**Дерево** — одна из наиболее широко распространённых структур данных в информатике, эмулирующая древовидную структуру в виде набора связанных узлов. Является связным графом, не содержащим циклы. Большинство источников также добавляет условие на то, что рёбра графа не должны быть ориентированными. В дополнение к этим трём ограничениям, в некоторых источниках указывается, что рёбра графа не должны быть взвешенными.
Деревья считаются **изоморфными** в том случае, если они имеют одинаковую структуру, но различный внешний вид.
| ![isomorphic trees](https://github.com/wortqp/IsomorphismOfTrees/blob/master/images/trees.png) |
| :--: |
| Рис. 1: Изоморфные деревья |

## 1.4 История
### 1.4.1 Первое использование и «открытие» теории графов
Швейцарский, прусский и российский математик Леонард Эйлер в статье (на латинском языке, изданной Петербургской академией наук) о решении знаменитой задачи о кёнигсбергских мостах, датированной 1736 годом, первым применил идеи теории графов при доказательстве некоторых утверждений. При этом Эйлер не использовал ни сам термин «граф», ни какие-либо термины теории графов, ни изображения графов. Леонард Эйлер считается отцом теории графов (как и топологии), открывшим понятие графа, а 1736 год назначен годом рождения теории графов.
### 1.4.2 Второе «открытие» графов
В 1847 году немецкий физик Густав Кирхгоф фактически разработал теорию деревьев при решении системы уравнений для нахождения величины силы тока в каждом контуре электрической цепи. Кирхгоф фактически изучал вместо электрической цепи соответствующий ей граф и показал, что для решения системы уравнений нет необходимости анализировать каждый цикл графа, достаточно рассмотреть только независимые циклы, определяемые любым остовным деревом графа.
### 1.4.3 Третье «открытие» графов
В 1857 году английский математик Артур Кэли, занимаясь практическими задачами органической химии, открыл важный класс графов — деревья. Кэли пытался перечислить химические изомеры предельных (насыщенных) углеводородов CnH2n+2 с фиксированным числом n атомов углерода.
### 1.4.4 Четвертое «открытие» графов
В 1859 году ирландский математик сэр Уильям Гамильтон придумал игру «Вокруг света». В этой игре использовался додекаэдр, каждая из 20 вершин которого соответствовала известному городу. От играющего требовалось обойти «вокруг света», то есть пройти по рёбрам додекаэдра так, чтобы пройти через каждую вершину ровно один раз.
### 1.4.5 Пятое «открытие» графов
В 1869 году, независимо от Артура Кэли, французский математик Камиль Жордан (в частности, в статье « Sur les assemblages de lignes ») придумал и исследовал деревья в рамках чистой математики. При этом Жордан использовал термины теории графов «вершина» (фр. sommet) и «ребро» (фр. arête), но вместо термина «граф» было «соединение рёбер» (фр. assemblage d’arêtes) или просто «соединение» (фр. assemblage). Рисунки Жордан не использовал. При этом Жордан не подозревал о значении своего открытия для органической химии.
### 1.4.6 Начало систематического использования слова «граф» и диаграмм графов
В начале XX века венгерский математик Денеш Кёниг первый предложил называть эти схемы «графами» и изучать их общие свойства. В 1936 году вышла первая в мире книга по теории графов (на немецком языке) Денеша Кёнига «Теория конечных и бесконечных графов» с большей частью результатов за прошедшие 200 лет, начиная с 1736 года — даты выхода первой статьи Леонарда Эйлера по теории графов с решением задачи о кёнигсбергских мостах. В частности, в книге Кёнига имеется общий параграф для задачи о кёнигсбергских мостах и задаче о домино (построение замкнутой цепи из всех костей домино по правилам игры).
## 1.5 Использование
Алгоритм определения **изоморфности деревьев** используется в основном как база для написания других алгоритмов, основанных на структуре дерева.
Одним из таких алгоритмов является алгоритм для **определения симметричности деревьев**.
## 1.6 Математическая модель и ограничения
Пусть дерево задается матрицей смежности. Два дерева с матрицами смежности $c_1$ и $c_2$ называются изоморфными, если существует такая перестановка $p$, что ∀i, j : $c_1[pi, pj] = c_2[i, j]$. Изоморфизм корневых деревьев определяется аналогично. Разница в том, что корень переходит в корень $(p_{root_1} = root_2)$.

Алгоритм работает за `O(n log n)`, при этому все, кроме сортировки работает за `O(n)`. Важно обратить внимание на выбор хеш функции. Пример: пусть есть дерево (1, 2), (1, 3), (1, 4) и дерево (1, 2), (1, 3), (3, 4), (3, 5). Хеш от вектора определяется функцией: 
$$31^n+\sum_{i=1}^{n} 31^{n-i}*a_i$$
`Деревья различны` и их `хэш функции различны`, из чего можно сделать вывод о том, что деревья не изоморфны.
Ограничения:
- Алгоритм определения изоморфности деревьев **требует корректности входных данных**. 
- Количество узлов дерева не может превышать $2^{32}$

-----------------------------
# 2. Поддерживаемые операции
- `isomorphic(otherTree)` — определение изоморфности текущего дерева с другим `(otherTree)`.

| isomorphic |
| ------- |
| $$O(nlog(n))$$ |
-----------------------------
# 3. Реализация
[Исходный код](https://github.com/wortqp/IsomorphismOfTrees).
## 3.1 Структура
### 3.1.1 Поля дерева (Tree)
- `nodes` - список (вектор) всех узлов в дереве.
- `numbers` - ассоциативный массив (Map) хешей сортированных списков (List) и их номеров.
```java
    private List<Node> nodes = new ArrayList<>();
    private Map<List<Integer>, Integer> numbers;
```
| ![fieldTree](https://github.com/wortqp/IsomorphismOfTrees/blob/master/images/fieldTree.png) |
| :--: |
| Рис. 2: Представление дерева в алгоритме. |

#### 3.1.2 Поля узла (Node)
- `subNodes` - список узлов, с которыми есть связь (ребра).

```java
    private List<Node> subNodes = new ArrayList<>();
```
| ![fieldSubNodes](https://github.com/wortqp/IsomorphismOfTrees/blob/master/images/fieldSubNodes.png) |
| :--: |
| Рис. 3: Связанные узлы узла 1. |
### 3.1.3 Конструктор
Конструктор считывает данные из файла в определенном формате и на их основе строит дерево.
Пример файла для дерева на Рис.2:

```
1: 2 5 8
2: 1 3 4
3: 2
4: 2
5: 1 6 7
6: 5
7: 5
8: 1
```
```java
public Tree(File file) {
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {

            String str;
            while ((str = in.readLine()) != null) {

                Matcher matcher = Pattern.compile("\\d+").matcher(str);
                if (matcher.find()) {
                    int node = Integer.parseInt(matcher.group());
                    add(node);
                    while (matcher.find()) {
                        int subNode = Integer.parseInt(matcher.group());
                        add(subNode);
                        List<Node> subNodes = get(node).subNodes;
                        subNodes.add(get(subNode));
                    }
                }

            }

            for (int i = 0; i < nodes.size(); i++)
                if (nodes.get(i) == null) {
                    nodes.remove(i);
                    i--;
                }
            try {
                isomorphic(this);
            } catch (StackOverflowError e) {
                nodes.clear();
                System.err.println("Invalid data tree have loop. File: " + file);
            }

        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
}
```
### 3.1.4 Вспомогательные функции
- `add(index)` - добавляет по индексу узел в список всех узлов в дереве.
- `get(index)` - берет узел по индексу из списка всех узлов в дереве.
- `toString()` - возвращает представление дерева в виде стоки.
- `Node.toString()` - возвращает представление узла в виде стоки.

```java
private void add(int index) {
    index--;
    for (;;)
        try {
            if (nodes.get(index) == null)
                nodes.set(index, new Node());
            return;
        } catch (IndexOutOfBoundsException e) {
            nodes.add(null);
        }
}
```
```java
private Node get(int index) {
    index--;
    return nodes.get(index);
}
```
```java
@Override
public String toString() {
    StringBuilder sb = new StringBuilder("Tree {\n");
    for (Node node: nodes) {
        sb.append("    ");
        sb.append(node);
        sb.append(" -> ");
        sb.append(node.subNodes);
        sb.append("\n");
    }
    sb.append("}");
    return sb.toString();
}
```
**Node**
```java
@Override
public String toString() {
    return "@" + hashCode();
}
```
## 3.2 Поддерживаемые операции
