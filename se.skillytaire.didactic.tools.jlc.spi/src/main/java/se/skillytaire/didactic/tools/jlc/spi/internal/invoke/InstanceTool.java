//package se.skillytaire.didactic.tools.jlc.spi.internal.invoke;
//
//import java.lang.annotation.Annotation;
//import java.util.stream.Stream;
//import java.util.stream.Stream.Builder;
//
//import se.skillytaire.didactic.tools.jlc.api.ComparableTestObjectFactory;
//import se.skillytaire.didactic.tools.jlc.api.GreaterThen;
//import se.skillytaire.didactic.tools.jlc.api.LessThen;
//import se.skillytaire.didactic.tools.jlc.api.TestObjectFactory;
//import se.skillytaire.didactic.tools.jlc.api.That;
//import se.skillytaire.didactic.tools.jlc.api.This;
//
//public class InstanceTool {
//    private final Object instance;
//   // private final TestObjectFactory<Object> objectFactory;
//    private ClassTool classTool;
//    
////    public InstanceTool(Object instance)   {
////       this(instance, null);
////    }
//    
//    public InstanceTool(Object instance) {
//       if(instance == null) {
//          throw new IllegalArgumentException("instance is void");
//       }
//       this.instance = instance;
//   //    this.objectFactory = defaultObjectFactory;
//       classTool= new ClassTool(instance.getClass());
//    }
//
//
//    
//    
//    
//    public Stream<InstanceFieldInitializer> instanceFieldInitializers(AnnotatedInvoker<?, ?,?> ai){
//          return classTool.fields(ai.getAnnotation())
//                  .map( field -> InstanceFieldInitializer
//                      .forInstance(instance)
//                      .using(field)
//                      .initialize(ai));
//    }
//    
//    public void autoInitialize() {
//    	
//    	
//    	
//    	
//       Builder<AnnotatedInvoker<?,?,?>> streamBuilder = Stream.builder();
//       
//       streamBuilder.add(new ThisInvoker<Object>())
//                 .add(new ThatInvoker<Object>());
//       if(this.objectFactory instanceof ComparableTestObjectFactory ) {
//          ComparableTestObjectFactory<Object> compF = (ComparableTestObjectFactory<Object>) this.objectFactory;
//          streamBuilder.add(new LessThenInvoker<Object>(compF))
//                    .add(new GreaterThenInvoker<Object>(compF));         
//       }
//
//       streamBuilder.build()
//                    .flatMap( ai -> instanceFieldInitializers(ai))
//                    .forEach(InstanceFieldInitializer::invoke);
//    }
//
//    public void validate() {
////       if(this.objectFactory != null && !(this.objectFactory instanceof ComparableTestObjectFactory) ) {
////          Builder<Class<? extends Annotation>> streamBuilder = Stream.builder();
////          streamBuilder.add(LessThen.class);
////          streamBuilder.add(GreaterThen.class);
////
////          StringBuilder msg = streamBuilder
////                .build()
////                .flatMap( c -> ct.fields(c))
////                .reduce(new StringBuilder(""), (builder, s) -> new StringBuilder(builder).append("The field '").append(s).append("' has not been initialized. \n\t"),
////                  (builder1, builder2) -> new StringBuilder(builder1).append(builder2));
////          if(msg.length() != 0) {
////             msg.append("Fields have @LessThen and/or @GreaterThen annotations, these are not supported for a non ComparableTestObjectFactory\n");
////             throw new IllegalStateException(msg.toString());
////          }
////
////       }
//       
//       Builder<Class<? extends Annotation>> streamBuilder = Stream.builder();
//       streamBuilder.add(This.class);
//       streamBuilder.add(That.class);
//       streamBuilder.add(LessThen.class);
//       streamBuilder.add(GreaterThen.class);
//       
//       
//       Class<Annotation>[] annotations = streamBuilder.build().toArray(Class[]::new);
//       StringBuilder msg = classTool.mutualExclusive(annotations).reduce(new StringBuilder(""), (builder, s) -> new StringBuilder(builder).append("The field '").append(s).append("' may only hava @This or @That or @LessThen or @GreaterThen, but not a combination. \n\t"),
//               (builder1, builder2) -> new StringBuilder(builder1).append(builder2));
//       if(msg.length() != 0) {
//          //msg.append("Fields have @LessThen and/or @GreaterThen annotations, these are not supported for a non ComparableTestObjectFactory\n");
//          throw new IllegalStateException(msg.toString());
//       }
//    }
//    
// }
// 