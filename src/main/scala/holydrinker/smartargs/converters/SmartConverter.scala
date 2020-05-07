package holydrinker.smartargs.converters

trait SmartConverter[T] {

  def convert(arg: String): T

}
