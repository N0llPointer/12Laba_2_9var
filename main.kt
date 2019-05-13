fun main(args: Array<String>){

    print("Введите количество точек на плоскости(минимум 3 точки)\n" +
            "N = ")
    val n = getInputValue().toInt()

    //весь список точек
    val coordinateList = getPointsList(n)

    calculate(coordinateList)

}


//получить значение из ввода
private fun getInputValue() = readLine()!!.toDouble()

//получить весь список точек
private fun getPointsList(n: Int): List<Pair<Double, Double>> {
    val list = mutableListOf<Pair<Double, Double>>()

    for (i in 1..n) {
        println("\nТочка #$i:")
        print("x = ")
        val x = getInputValue()

        print("y = ")
        val y = getInputValue()

        list.add(Pair(x, y))
    }

    return list
}


fun calculate(coordinateList: List<Pair<Double,Double>>){

    val list = mutableListOf<TrianglePerimetr>()
    for(i in coordinateList.withIndex()){
        for(j in coordinateList.withIndex()){
            if(i.index == j.index)
                continue
            for(k in coordinateList.withIndex()){
                if(k.index == i.index || k.index == j.index)
                    continue
                val triangle = TrianglePerimetr(i.value,j.value,k.value)
                if(!list.contains(triangle))
                    list.add(triangle)
            }
        }
    }

    var maxTriangle = list[0]
    for(triangle in list){
        if(triangle.compareTo(maxTriangle) > 0)
            maxTriangle = triangle
    }

   println("\nA(${maxTriangle.firstCoordinate}\n" +
            "B(${maxTriangle.secondCoordinate}\n" +
            "C(${maxTriangle.thirdCoordinate}\n" +
            "P = ${maxTriangle.calcluatePerimetr()}")


}


data class TrianglePerimetr(val firstCoordinate: Pair<Double,Double> ,val secondCoordinate: Pair<Double,Double> ,val thirdCoordinate: Pair<Double,Double>){

    fun compareTo(value: TrianglePerimetr): Int{
        val initialLength = this.calcluatePerimetr()
        val compareLength = value.calcluatePerimetr()

        if(initialLength > compareLength)
            return 1
        else if(initialLength == compareLength)
            return 0
        else return -1

    }

    override fun equals(other: Any?): Boolean {
        val value = other as TrianglePerimetr
        return this.contains(value.firstCoordinate) && this.contains(value.secondCoordinate) &&this.contains(value.thirdCoordinate)
    }

    fun contains(value: Pair<Double,Double>): Boolean{
        return firstCoordinate == value || secondCoordinate == value || thirdCoordinate == value
    }

    fun calcluatePerimetr(): Double{
        val firstLength = Math.sqrt(Math.pow(firstCoordinate.first - secondCoordinate.second,2.0) + Math.pow(firstCoordinate.second - secondCoordinate.second,2.0))
        val secondLength = Math.sqrt(Math.pow(secondCoordinate.first - thirdCoordinate.second,2.0) + Math.pow(secondCoordinate.second - thirdCoordinate.second,2.0))
        val thirdLength = Math.sqrt(Math.pow(firstCoordinate.first - thirdCoordinate.second,2.0) + Math.pow(firstCoordinate.second - thirdCoordinate.second,2.0))
        return firstLength + secondLength + thirdLength
    }
}
