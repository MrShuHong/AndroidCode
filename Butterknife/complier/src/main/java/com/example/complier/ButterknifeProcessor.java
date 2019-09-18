package com.example.complier;

import com.example.annotations.BindView;
import com.example.annotations.OnClick;
import com.google.auto.service.AutoService;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

/**
 * File description.
 *
 * @author dsh
 * @date 2019-09-05
 */
@AutoService(Processor.class)
public class ButterknifeProcessor extends AbstractProcessor {


    private Filer mEnvFiler;
    private Elements mElementUtils;
    private Messager mMessager;

    @Override
    public synchronized void init(ProcessingEnvironment env) {
        super.init(env);
        mEnvFiler = env.getFiler();
        mElementUtils = env.getElementUtils();
        mMessager = env.getMessager();

        mMessager.printMessage(Diagnostic.Kind.NOTE, "ButterknifeProcessor init()");
    }


    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> supportedAnnotationTypes = new HashSet<>();
        supportedAnnotationTypes.add(BindView.class.getCanonicalName());
        supportedAnnotationTypes.add(OnClick.class.getCanonicalName());
        return supportedAnnotationTypes;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }


    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        Set<? extends Element> elementsAnnotatedWith = roundEnvironment.getElementsAnnotatedWith(BindView.class);

        Iterator<? extends Element> iterator = elementsAnnotatedWith.iterator();
        Map<String, List<VariableElement>> activityMap = new HashMap<>();
        while (iterator.hasNext()) {
            VariableElement variableElement = (VariableElement) iterator.next();
            //对应的activity
            TypeElement typeElement = (TypeElement) variableElement.getEnclosingElement();
            TypeMirror typeMirror = typeElement.asType();
            //全路径
            String activityPullName = typeMirror.toString();
            List<VariableElement> variableElements = activityMap.get(activityPullName);
            if (variableElements == null) {
                variableElements = new ArrayList<>();
                activityMap.put(activityPullName, variableElements);
            }
            variableElements.add(variableElement);
        }

        if (!activityMap.isEmpty()) {
            Iterator<String> activityIterator = activityMap.keySet().iterator();
            while (activityIterator.hasNext()) {
                String activityFullName = activityIterator.next();
                mMessager.printMessage(Diagnostic.Kind.NOTE, activityFullName);
                List<VariableElement> variableElements = activityMap.get(activityFullName);
                Element enclosingElement = variableElements.get(0).getEnclosingElement();
                String activityName = enclosingElement.getSimpleName().toString();
                String packageName = mElementUtils.getPackageOf(enclosingElement).toString();
                Writer writer = null;
                try {
                   // Class<?> activityClass = Class.forName(activityFullName);

                    JavaFileObject classFile = mEnvFiler.createSourceFile(activityName + "$$ViewBinder");

                    writer = classFile.openWriter();
                    writer.write("package " + packageName + ";\n\n\n");
                    writer.write("import " + packageName + ".ViewBinder;\n\n\n");
                    writer.write("public class " + activityName + "$$ViewBinder<T extends " + activityName + "> implements ViewBinder<T> {\n\n");
                    writer.write("    @Override\n");
                    writer.write("    public void bind(final T target) {\n");

                    for (VariableElement variableElement : variableElements) {
                        String viewName = variableElement.getSimpleName().toString();
                        TypeMirror typeMirror = variableElement.asType();
                        int viewId = variableElement.getAnnotation(BindView.class).value();
                        writer.write("        target." + viewName + " =(" + typeMirror + ")target.findViewById(" + viewId + ");\n");
                    }

                    writer.write("    }\n");
                    writer.write("}");
                }  catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (writer != null) {
                        try {
                            writer.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }


        return false;
    }
}


/*
package com.example.butterknife;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class MainActivity$$ViewBinder<T extends MainActivity> implements ViewBinder<T> {
    @Override
    public Unbinder bind(final Finder finder, final T target, Object source) {
        InnerUnbinder unbinder = createUnbinder(target);
        View view;
        view = finder.findRequiredView(source, 2131165325, "field 'txt_hello'");
        target.txt_hello = finder.castView(view, 2131165325, "field 'txt_hello'");
        return unbinder;
    }

    protected InnerUnbinder<T> createUnbinder(T target) {
        return new InnerUnbinder(target);
    }

    protected static class InnerUnbinder<T extends MainActivity> implements Unbinder {
        private T target;

        protected InnerUnbinder(T target) {
            this.target = target;
        }

        @Override
        public final void unbind() {
            if (target == null) throw new IllegalStateException("Bindings already cleared.");
            unbind(target);
            target = null;
        }

        protected void unbind(T target) {
            target.txt_hello = null;
        }
    }
}
*/
